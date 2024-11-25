package com.example.silenokkombat.service;

import com.example.silenokkombat.dto.RegistrationDto;
import com.example.silenokkombat.entity.User;
import com.example.silenokkombat.entity.UserInformation;
import com.example.silenokkombat.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationService {
    private final UserInformationRepository userInformationRepository;

    public void createNewUserInformation(RegistrationDto registrationUserDto, User user){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail(registrationUserDto.getEmail());
        userInformation.setUser(user);
        userInformationRepository.save(userInformation);
    }

    public Optional<UserInformation> findByEmail(String email) {
        return userInformationRepository.findByEmail(email);
    }

    public void save(UserInformation client){
        userInformationRepository.save(client);
    }
}
