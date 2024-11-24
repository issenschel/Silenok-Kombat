package com.example.silenokkombat.interfuck;

import com.example.silenokkombat.dto.RegistrationDto;

import java.util.Optional;

@FunctionalInterface
public interface ValidationRule {
    Optional<String> validate(RegistrationDto registrationUserDto);
}
