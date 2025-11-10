package com.example.modeule2.service.Impl;

import com.example.modeule2.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String email,
                         String body) {
        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MailService");
        message.setText(body);

        mailSender.send(message);
        log.info("Mail sent successfully ");
    }

    @Override
    public void sendMailDeleteUser(String email) {
        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MailService");
        message.setText("Здравствуйте! Ваш аккаунт был удалён.");

        mailSender.send(message);
        log.info("Mail sent successfully ");
    }

    @Override
    public void sendMailCreateUser(String email) {
        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MailService");
        message.setText("Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.");

        mailSender.send(message);
        log.info("Mail sent successfully ");
    }

}
