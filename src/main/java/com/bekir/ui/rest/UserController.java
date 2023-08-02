package com.bekir.ui.rest;

import com.bekir.business.dto.UserDto;
import com.bekir.business.dto.UserPojo;
import com.bekir.business.dto.UserSaveDto;
import com.bekir.business.services.UserServices;
import com.bekir.data.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "User Api Documentation")
public class UserController {

    @Autowired
    UserServices userServices;

    // List
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    @ApiOperation(value = "List users")
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userServices.getAllUsers();
        return userDtoList;
    }

    // Find
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    @ApiOperation(value = "Find user by id")
    public ResponseEntity<UserPojo> getUserById(@PathVariable Long id) throws Throwable {
        try {
            UserSaveDto userSaveDto = userServices.getUserById(id);

            if(userSaveDto == null)
                return new ResponseEntity<>(new UserPojo(false, null, "User not found with id " + id), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new UserPojo(true, userSaveDto, "OK"), HttpStatus.OK);
        }
        catch (Error e) {
            return new ResponseEntity<>(new UserPojo(false, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Save
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    @ApiOperation(value = "Create new user")
    public ResponseEntity<UserPojo> createUser(@RequestBody UserSaveDto userDto){
        try {
            userServices.createUser(userDto);
            return new ResponseEntity<>(new UserPojo(true, userDto, "OK"), HttpStatus.OK);
        }
        catch (Error e) {
            return new ResponseEntity<>(new UserPojo(false, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update
    // http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    @ApiOperation(value = "Update user")
    public ResponseEntity<UserPojo> updateUser(@PathVariable Long id, @RequestBody UserSaveDto userSaveDto) throws Throwable {
        try {
            UserSaveDto updatedUser = userServices.updateUser(id, userSaveDto);
            return new ResponseEntity<>(new UserPojo(true, updatedUser, "OK"), HttpStatus.OK);
        }
        catch (Error e) {
            return new ResponseEntity<>(new UserPojo(false, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete
    // http://localhost:8080/api/v1/users/1
    @DeleteMapping("/users/{id}")
    @ApiOperation(value = "Delete user")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id) throws Throwable {
        userServices.deleteUser(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
