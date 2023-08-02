package com.bekir.business.services.impl;

import com.bekir.business.dto.UserDto;
import com.bekir.business.dto.UserSaveDto;
import com.bekir.business.services.UserServices;
import com.bekir.data.entity.UserEntity;
import com.bekir.exception.repository.UserRepository;
import com.bekir.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    // List
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> listDto = new ArrayList<>();
        Iterable<UserEntity> entityList = userRepository.findAll();

        for (UserEntity entity:entityList) {
            UserDto userDto = UserEntitytoUserDto(entity);
            listDto.add(userDto);
        }

        return listDto;
    }

    // Save
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    @Override
    public UserSaveDto createUser(@RequestBody UserSaveDto userSaveDto) {
        UserEntity userEntity = DtoToEntity(userSaveDto);
        userRepository.save(userEntity);

        return userSaveDto;
    }

    // Find
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    @Override
    public UserSaveDto getUserById(@PathVariable Long id) {
        try {
            Optional<UserEntity> optionalEntity = userRepository.findById(id);

            if(optionalEntity.isEmpty())
                return null;

            UserEntity user = optionalEntity.get();

            UserDto userDto = UserEntitytoUserDto(user);

            UserSaveDto userSaveDto = new UserSaveDto();
            userSaveDto.setIdentityNumber(userDto.getIdentityNumber());
            userSaveDto.setName(userDto.getName());
            userSaveDto.setSurname(userDto.getSurname());
            userSaveDto.setRevenue(userDto.getRevenue());
            userSaveDto.setPhoneNumber(userDto.getPhoneNumber());
            userSaveDto.setBirthDate(userDto.getBirthDate());
            userSaveDto.setWarrantPrice(userDto.getWarrantPrice());

            return userSaveDto;
        }
        catch (Error e) {
            return null;
        }
    }

    // Update
    // http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    @Override
    public UserSaveDto updateUser(@PathVariable Long id, @RequestBody UserSaveDto userDetails) {
        UserEntity userEntity = DtoToEntity(userDetails); // ModelMapper
        Optional<UserEntity> optionalEntity = userRepository.findById(id);

        if(optionalEntity.isEmpty())
            return null;

        UserEntity user = optionalEntity.get();

        user.setIdentityNumber(userEntity.getIdentityNumber());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setRevenue(userEntity.getRevenue());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setBirthDate(userEntity.getBirthDate());
        user.setWarrantPrice(userEntity.getWarrantPrice());

        UserEntity updatedUser = userRepository.save(user);

        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setIdentityNumber(updatedUser.getIdentityNumber());
        userSaveDto.setName(updatedUser.getName());
        userSaveDto.setSurname(updatedUser.getSurname());
        userSaveDto.setRevenue(updatedUser.getRevenue());
        userSaveDto.setPhoneNumber(updatedUser.getPhoneNumber());
        userSaveDto.setBirthDate(updatedUser.getBirthDate());
        userSaveDto.setWarrantPrice(updatedUser.getWarrantPrice());

        return userSaveDto;
    }

    // Delete
    // http://localhost:8080/api/v1/users/1
    @DeleteMapping("/users/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) throws Throwable {
        UserEntity user = (UserEntity) userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id " + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    ////////////////////////////////////////////////////////////////////////////////////////
    // Model Mapper
    // Entity => DTO
    @Override
    public UserDto UserEntitytoUserDto(UserEntity userEntity) {
        UserDto userDto =  modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserSaveDto UserEntitytoUserSaveDto(UserEntity userEntity) {
        UserSaveDto userDto =  modelMapper.map(userEntity, UserSaveDto.class);
        return userDto;
    }

    // DTO => Entity
    // Overloading
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

    // Overloading
    @Override
    public UserEntity DtoToEntity(UserSaveDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }
}
