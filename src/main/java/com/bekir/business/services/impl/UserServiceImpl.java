package com.bekir.business.services.impl;

import com.bekir.business.dto.UserDto;
import com.bekir.business.services.UserServices;
import com.bekir.data.entity.UserEntity;
import com.bekir.data.repository.UserRepository;
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
            UserDto userDto = EntitytoDto(entity);
            listDto.add(userDto);
        }

        return listDto;
    }

    // Save
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    @Override
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = DtoToEntity(userDto);
        userRepository.save(userEntity);
        return userDto;
    }

    // Find
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    @Override
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws Throwable {
        UserEntity user = (UserEntity) userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id " + id));
        UserDto userDto = EntitytoDto(user);
        return ResponseEntity.ok(userDto);
    }

    // Update
    // http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDetails) throws Throwable {
        UserEntity userEntity = DtoToEntity(userDetails); // ModelMapper
        UserEntity user = (UserEntity) userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id " + id));

        user.setIdentityNumber(userEntity.getIdentityNumber());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setRevenue(userEntity.getRevenue());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setBirthDate(userEntity.getBirthDate());
        user.setWarrantPrice(userEntity.getWarrantPrice());

        UserEntity updatedUser = (UserEntity) userRepository.save(user);
        UserDto userDto = EntitytoDto(updatedUser);

        return ResponseEntity.ok(userDto);
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
    public UserDto EntitytoDto(UserEntity userEntity) {
        UserDto userDto =  modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    // DTO => Entity
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }
}
