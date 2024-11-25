package com.example.silenokkombat.service;

import com.example.silenokkombat.dto.AuthTokenDto;
import com.example.silenokkombat.dto.JwtRequestDto;
import com.example.silenokkombat.dto.MessageDto;
import com.example.silenokkombat.dto.RegistrationDto;
import com.example.silenokkombat.entity.User;
import com.example.silenokkombat.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserInformationService userInformationService;
    private final UserTokenService userTokenService;
    private final AssumptionService assumptionService;
    private final PlayerService playerService;


    public AuthTokenDto createAuthToken(@RequestBody JwtRequestDto authRequest) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        Optional<User> user = userService.findByUsername(authRequest.getUsername());
        String versionId = user.get().getUserToken().getVersionToken();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String token = jwtTokenUtil.generateToken(userDetails, versionId);
        return new AuthTokenDto(token,roles);
    }

    @Transactional
    public MessageDto createNewUser(@RequestBody RegistrationDto registrationUserDto) {
        User user = userService.createNewUser(registrationUserDto.getUsername(), registrationUserDto.getPassword());
        userInformationService.createNewUserInformation(registrationUserDto, user);
        playerService.createNewPlayer(user);
        String token = UUID.randomUUID().toString();
        userTokenService.createNewTokenVersion(user,token);
        assumptionService.findByEmail(registrationUserDto.getEmail()).ifPresent(assumptionService::delete);
        return new MessageDto("Вы успешно зарегистрировались");
    }
}
