package com.example.doan.services.imp;

import com.example.doan.services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public String sendMailWithText(String sub, String content, String to) {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setSubject(sub);
            mail.setText(content);
            mail.setTo(to);
            emailSender.send(mail);
        }catch (Exception e){
            return "Send failed";
        }
        return "Send success";
    }
}
