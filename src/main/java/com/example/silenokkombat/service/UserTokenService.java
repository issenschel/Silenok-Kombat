package com.example.silenokkombat.service;

import com.example.silenokkombat.entity.User;
import com.example.silenokkombat.entity.UserToken;
import com.example.silenokkombat.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTokenService {
    private final UserTokenRepository userTokenRepository;

    public Optional<UserToken> findByVersion(String token) {
        return userTokenRepository.findByVersionToken(token);
    }

    public void createNewTokenVersion(User user, String token){
        UserToken tokenVersion = new UserToken();
        tokenVersion.setUser(user);
        tokenVersion.setVersionToken(token);
        userTokenRepository.save(tokenVersion);
    }

    public void save(UserToken userToken) {
        userTokenRepository.save(userToken);
    }
}
