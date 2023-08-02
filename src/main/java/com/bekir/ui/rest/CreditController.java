package com.bekir.ui.rest;

import com.bekir.business.dto.CreditApplicationInfoDto;
import com.bekir.business.dto.CreditDto;
import com.bekir.business.services.CreditServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class CreditController {

    @Autowired
    CreditServices creditServices;


    // Save
    // http://localhost:8080/api/v1/credits
    @PostMapping("/credits")
    @ApiOperation(value = "Apply credit")
    public CreditDto createCredit(@RequestBody CreditApplicationInfoDto creditApplicationInfoDto){
        CreditDto creditDto = creditServices.createCredit(creditApplicationInfoDto);
        return creditDto;
    }
}
