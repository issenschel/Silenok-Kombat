package com.example.silenokkombat.controller;

import com.example.silenokkombat.dto.EmailDto;
import com.example.silenokkombat.dto.MessageDto;
import com.example.silenokkombat.service.EmailService;
import com.example.silenokkombat.service.RegistrationValidatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final RegistrationValidatorService registrationValidatorService;
    private final EmailService emailService;

    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new MessageDto(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
        }
        Optional<String> validate = registrationValidatorService.validateEmail(emailDto);
        return validate.map(s -> ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageDto(s)))
                .orElseGet(() -> ResponseEntity.ok().body(emailService.sendCode(emailDto.getEmail())));
    }
}
