package com.bekir.business.services;

import com.bekir.business.dto.CreditDto;
import com.bekir.business.dto.UserDto;
import com.bekir.data.entity.CreditEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CreditServices {

    // CRUD
    public CreditDto createCredit(UserDto userDto);

    // model mapper
    public CreditDto EntitytoDto(CreditEntity creditEntity);
    public CreditEntity DtoToEntity(CreditDto creditDto);
}
