package com.credibanco.bankinc.util;

import java.util.Random;

public class RandomNumberGenerator {
    public static Long generateRandomNumber(Long productId) {
        Random random = new Random();
        long randomNumber = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return Long.parseLong(productId.toString() + randomNumber);
    }
}