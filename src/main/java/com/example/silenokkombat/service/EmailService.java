package com.example.silenokkombat.service;

import com.example.silenokkombat.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final AssumptionService assumptionService;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kptcsmp@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Transactional
    public MessageDto sendCode(String email){
        String key = generateVerificationCode();
        sendSimpleMessage(email, "Код подтверждения", "Код: " + key);
        assumptionService.saveOrUpdate(email, key);
        return new MessageDto("Код отправлен");
    }

    public String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

    public boolean validateCode(String email, String code) {
        return assumptionService.findByEmail(email).filter(assumption -> assumption.getCode().equals(code)).isPresent();
    }
}
