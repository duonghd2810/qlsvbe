package com.example.doan.utils;

import java.time.LocalDate;
import java.util.Random;

public class GenaralDataUser {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    public static String generateUsername(){
        int year = LocalDate.now().getYear();
        StringBuilder username = new StringBuilder(String.valueOf(year));
        Random random= new Random();
        int valueRandom = random.nextInt()*10000;
        username.append(String.valueOf(valueRandom));
        return username.toString();
    }
    public static String generatePassword(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    public static int randomNumber(int min, int max) {
        Random random= new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
