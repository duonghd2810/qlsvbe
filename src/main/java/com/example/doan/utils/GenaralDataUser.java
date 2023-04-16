package com.example.doan.utils;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenaralDataUser {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    public static String generateStudent(){
        int year = LocalDate.now().getYear();
        StringBuilder username = new StringBuilder(String.valueOf(year));
        int valueRandom = ThreadLocalRandom.current().nextInt(10000,100000);;
        username.append(valueRandom);
        return username.toString().replace("-","");
    }
    public static String generateTeacher(){
        StringBuilder username = new StringBuilder("GV");
        int valueRandom = ThreadLocalRandom.current().nextInt(10000,100000);;
        username.append(valueRandom);
        return username.toString().replace("-","");
    }
    public static String generateAdmin(){
        StringBuilder username = new StringBuilder("Admin");
        int valueRandom = ThreadLocalRandom.current().nextInt(10000,100000);;
        username.append(valueRandom);
        return username.toString().replace("-","");
    }
    public static String generateSubjecCode(){
        StringBuilder hp = new StringBuilder("HP");
        for(int i = 0; i < 3; i++){
            int number = randomNumber(0, digits.length() - 1);
            char ch = digits.charAt(number);
            hp.append(ch);
        }
        return hp.toString().replace("-","");
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
