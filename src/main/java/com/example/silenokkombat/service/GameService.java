package com.example.silenokkombat.service;

import com.example.silenokkombat.dto.LeaderboardDto;
import com.example.silenokkombat.dto.StatusResponseDto;
import com.example.silenokkombat.entity.Player;
import com.example.silenokkombat.entity.Skin;
import com.example.silenokkombat.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final PlayerService playerService;
    private final UserService userService;
    private final SkinService skinService;
    private final LeaderboardService leaderboardService;

    public Player getPlayer(){
        Optional<User> user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.map(User::getPlayer).orElse(null);
    }

    @Transactional
    public StatusResponseDto changeCoin(Long coin){
        Optional<User> user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.map(us ->{
             playerService.changeCoin(us,coin);
             return new StatusResponseDto("Успешно", HttpStatus.OK);
        }).orElseGet(() -> new StatusResponseDto("Пользователь не найден", HttpStatus.OK));
    }

    @Transactional
    public StatusResponseDto buySkin(Byte id){
        Optional<User> user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.map(us -> {
           Optional<Skin> skin = skinService.findById(id);
            return skin.map(sk -> {
                boolean result = playerService.buySkin(us, sk);
                if(result){
                    return new StatusResponseDto("Успешно", HttpStatus.OK);
                }
                return new StatusResponseDto("Недостаточно средств", HttpStatus.OK);
            }).orElse(new StatusResponseDto("Скин не найден", HttpStatus.NOT_FOUND));
        }).orElseGet(() -> new StatusResponseDto("Пользователь не найден", HttpStatus.OK));
    }

    public List<LeaderboardDto> getLeaderboard(){
        return leaderboardService.getLeaderboard();
    }

}
