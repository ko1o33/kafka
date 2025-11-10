package com.example.modeule2.kafka;

import com.example.modeule2.dto.UserDto;
import com.example.modeule2.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final MailService mailService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "mail_crate", groupId = "mail")
    public void listenMailCreate(String json, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            var userDto = objectMapper.readValue(json, UserDto.class);
            log.info("Received transaction. Key: {}, Event: {}", userDto.toString(), topic);
            mailService.sendMailCreateUser(userDto.getEmail());
        }catch (Exception e){
            log.info("mistake");
        }
    }

    @KafkaListener(topics = "mail_delete", groupId = "mail")
    public void listenMailDelete(String json, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            var userDto = objectMapper.readValue(json, UserDto.class);
            log.info("Received transaction. Key: {}, Event: {}", userDto.toString(), topic);
            mailService.sendMailDeleteUser(userDto.getEmail());
        }catch (Exception e){
            log.info("mistake");
        }
    }
}
