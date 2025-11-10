package com.example.modeule2.service;

public interface MailService {
    void sendMail(String email,String body);
    void sendMailDeleteUser(String email);
    void sendMailCreateUser(String email);
}
