package com.bekir.business.services;

import com.bekir.business.dto.UserDto;
import com.bekir.data.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserServices {

    // CRUD
    public List<UserDto> getAllUsers();
    public UserDto createUser(UserDto userDto);
    public ResponseEntity<UserDto> getUserById(Long id) throws Throwable;
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) throws Throwable;
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id) throws Throwable;

    // model mapper
    public UserDto EntitytoDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);
}
