package com.example.modeule2.controller;

import com.example.modeule2.dto.EmailDto;
import com.example.modeule2.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @Operation(
            summary = "Отправить  письмо",
            description = "Отправляет письмо по указоному email"
    )
    @PostMapping("/sendMail")
    public void sendMail(@RequestBody EmailDto emailDto) {
        mailService.sendMail(emailDto.getEmail(),emailDto.getMessage());
    }

    @Operation(
            summary = "Отправить  письмо о удаление акаунта",
            description = "Отправляет письмо по указоному email, что акаунт удален"
    )
    @GetMapping("/deleteUser")
    public void sendMailDeleteUser(@RequestParam String email) {
        mailService.sendMailDeleteUser(email);
    }

    @Operation(
            summary = "Отправить  письмо о создание акаунта",
            description = "Отправляет письмо по указоному email, что акаунт создан"
    )
    @GetMapping("/createUser")
    public void sendMailCreateUser(@RequestParam String email) {
        mailService.sendMailCreateUser(email);
    }
}
