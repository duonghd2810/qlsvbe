package com.example.doan.services;

public interface IMailService {
    String sendMailWithText(String sub,String content, String to);
}
