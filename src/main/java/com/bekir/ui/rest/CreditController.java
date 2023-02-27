package com.bekir.ui.rest;

import com.bekir.business.dto.CreditDto;
import com.bekir.business.dto.UserDto;
import com.bekir.business.services.CreditServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class CreditController {

    @Autowired
    CreditServices creditServices;


    // Save
    // http://localhost:8080/api/v1/credits
    @PostMapping("/credits")
    public CreditDto createCredit(@RequestBody UserDto UserDto){
        CreditDto creditDto = creditServices.createCredit(UserDto);
        return creditDto;
    }
}
