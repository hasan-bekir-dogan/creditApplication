package com.bekir.ui.rest;

import com.bekir.business.dto.UserDto;
import com.bekir.business.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserServices userServices;

    // List
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userServices.getAllUsers();
        return userDtoList;
    }

    // Find
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws Throwable {
        ResponseEntity<UserDto> userDto = userServices.getUserById(id);
        return userDto;
    }


    // Save
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto){
        userServices.createUser(userDto);
        return userDto;
    }

    // Update
    // http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws Throwable {
        userServices.updateUser(id,userDto);
        return ResponseEntity.ok(userDto);
    }

    // Delete
    // http://localhost:8080/api/v1/users/1
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id) throws Throwable {
        userServices.deleteUser(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
