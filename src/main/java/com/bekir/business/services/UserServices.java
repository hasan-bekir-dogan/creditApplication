package com.bekir.business.services;

import com.bekir.business.dto.UserDto;
import com.bekir.business.dto.UserSaveDto;
import com.bekir.data.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserServices {

    // CRUD
    public List<UserDto> getAllUsers();
    public UserSaveDto createUser(UserSaveDto userDto);
    public UserSaveDto getUserById(Long id) throws Throwable;
    public UserSaveDto updateUser(Long id, UserSaveDto userDto) throws Throwable;
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id) throws Throwable;

    // model mapper
    public UserDto UserEntitytoUserDto(UserEntity userEntity);
    public UserSaveDto UserEntitytoUserSaveDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);
    public UserEntity DtoToEntity(UserSaveDto userDto);
}
