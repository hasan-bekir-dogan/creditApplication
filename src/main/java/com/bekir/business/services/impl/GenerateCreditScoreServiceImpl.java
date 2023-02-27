package com.bekir.business.services.impl;

import com.bekir.business.services.GenerateCreditScoreServices;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateCreditScoreServiceImpl implements GenerateCreditScoreServices {
    // return credit score
    @Override
    public int getCreditScore() {
        Random randomNum = new Random();
        int creditScore = randomNum.nextInt(2000);

        return creditScore;
    }


}
