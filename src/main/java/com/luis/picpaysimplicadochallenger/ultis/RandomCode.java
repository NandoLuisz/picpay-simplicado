package com.luis.picpaysimplicadochallenger.ultis;

import java.util.Random;

public class RandomCode {

    private final Random random = new Random();

    public Integer randomCodeGenerated(){
        return Math.abs(100000 + random.nextInt(900000));
    }
}
