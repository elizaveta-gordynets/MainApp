package com.example.mainapp.util;

import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor
public final class GeneralUtils {

    public static String generateCode() {
        String code;
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;

            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(
                    "Problem when generating the random code.");
        }
        return code;
    }
}
