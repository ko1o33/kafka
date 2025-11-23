package com.example.modeule2.service;

import com.example.modeule2.service.Impl.MailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailServiceImpl mailService;

    private String testEmail;
    private String testBody;

    @BeforeEach
    void setUp() {
        testEmail = "test@example.com";
        testBody = "Test email body";
    }

    @Test
    void sendMail() {
        mailService.sendMail(testEmail, testBody);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendMailDeleteUser() {
        mailService.sendMailDeleteUser(testEmail);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendMailCreateUser() {
        mailService.sendMailCreateUser(testEmail);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }


}
