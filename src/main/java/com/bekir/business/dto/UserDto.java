package com.bekir.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

// DTO: Data Transfer Object
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class UserDto {
    private Long id;
    private Long identityNumber;
    private String name;
    private String surname;
    private double revenue;
    private Long phoneNumber;
    private String birthDate;
    private double warrantPrice;
}
