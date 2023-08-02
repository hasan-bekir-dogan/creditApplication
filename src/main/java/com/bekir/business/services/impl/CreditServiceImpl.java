package com.bekir.business.services.impl;

import com.bekir.business.dto.CreditApplicationInfoDto;
import com.bekir.business.dto.CreditDto;
import com.bekir.business.services.CreditServices;
import com.bekir.business.services.GenerateCreditScoreServices;
import com.bekir.data.entity.CreditEntity;
import com.bekir.data.entity.UserEntity;
import com.bekir.exception.repository.CreditRepository;
import com.bekir.exception.repository.UserRepository;
import com.bekir.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
public class CreditServiceImpl implements CreditServices {

    public final int credit_limit_multiplier = 4;
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    GenerateCreditScoreServices generateCreditScoreServices;

    // Save
    // http://localhost:8080/api/v1/credits
    @PostMapping("/credits")
    @Override
    public CreditDto createCredit(@RequestBody CreditApplicationInfoDto creditApplicationInfoDto) {
        UserEntity user = (UserEntity) userRepository.findUserByIdentityNumberAndBirthDate(creditApplicationInfoDto.getIdentityNumber(), creditApplicationInfoDto.getBirthDate());
        double creditLimit = 0;
        char acceptedyn = 'N';

        if(user == null)
            throw new ResourceNotFoundException("User not exist with identity number: " + creditApplicationInfoDto.getIdentityNumber() + " and birthdate: " + creditApplicationInfoDto.getBirthDate());

        int creditScore = generateCreditScoreServices.getCreditScore();

        if(creditScore < 500){
            throw new ResourceNotFoundException("Credit status is failed");
        }
        else if(creditScore > 500 && creditScore < 1000){
            if(user.getRevenue() < 5000) {
                creditLimit = 10000;
                acceptedyn = 'Y';
                if (user.getWarrantPrice() != 0) {
                    creditLimit += user.getWarrantPrice() * 10 / 100;
                }
            }
            else if(user.getRevenue() > 5000 && user.getRevenue() < 10000) {
                creditLimit = 20000;
                acceptedyn = 'Y';
                if (user.getWarrantPrice() != 0) {
                    creditLimit += user.getWarrantPrice() * 20 / 100;
                }
            }
            else if(user.getRevenue() > 10000) {
                creditLimit = user.getRevenue() * credit_limit_multiplier / 2;
                acceptedyn = 'Y';
                if (user.getWarrantPrice() != 0) {
                    creditLimit += user.getWarrantPrice() * 25 / 100;
                }
            }
        }
        else if(creditScore >= 1000){
            creditLimit = user.getRevenue() * credit_limit_multiplier;
            acceptedyn = 'Y';
            if (user.getWarrantPrice() != 0) {
                creditLimit += user.getWarrantPrice() * 50 / 100;
            }
        }

        System.out.println("Credit details sent to your phone number");

        CreditDto creditDto = new CreditDto(null, user.getId(), creditLimit, acceptedyn);

        if(acceptedyn == 'Y') {
            CreditEntity creditEntity = DtoToEntity(creditDto);

            creditRepository.save(creditEntity);
        }

        return creditDto;
    }


    ////////////////////////////////////////////////////////////////////////////////////////
    // Model Mapper
    // Entity => DTO
    @Override
    public CreditDto EntitytoDto(CreditEntity creditEntity) {
        CreditDto creditDto = modelMapper.map(creditEntity, CreditDto.class);
        return creditDto;
    }

    // DTO => Entity
    @Override
    public CreditEntity DtoToEntity(CreditDto creditDto) {
        CreditEntity creditEntity = modelMapper.map(creditDto, CreditEntity.class);
        return creditEntity;
    }
}
