package com.bekir.data.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@Log4j2


@Entity
@Table(name="users")
public class UserEntity extends BaseEntity implements Serializable{

    @Column(name="identity_number")
    private Long identityNumber;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="revenue")
    private double revenue;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="birth_date")
    private String birthDate;

    @Column(name="warrant_price")
    private double warrantPrice;

    public UserEntity(Long identityNumber, String name, String surname, double revenue, Long phoneNumber, String birthDate, double warrantPrice) {
        this.identityNumber = identityNumber;
        this.name = name;
        this.surname = surname;
        this.revenue = revenue;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.warrantPrice = warrantPrice;
    }
}
