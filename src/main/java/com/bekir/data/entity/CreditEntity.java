package com.bekir.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@Log4j2


@Entity
@Table(name="credits")
public class CreditEntity extends BaseEntity implements Serializable{

    @Column(name="user_id")
    private Long userId;

    @Column(name="credit_limit")
    private double creditLimit;

    @Column(name="accepted_yn")
    private char acceptedyn;

    public CreditEntity(Long userId, double creditLimit, char acceptedyn) {
        this.userId = userId;
        this.creditLimit = creditLimit;
        this.acceptedyn = acceptedyn;
    }
}
