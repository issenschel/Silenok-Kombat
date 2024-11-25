package com.example.silenokkombat.service;

import com.example.silenokkombat.entity.Player;
import com.example.silenokkombat.entity.Skin;
import com.example.silenokkombat.entity.User;
import com.example.silenokkombat.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final SkinService skinService;

    public void changeCoin(User user, Long coin) {
        playerRepository.findByUser(user).ifPresent(player -> {
                player.setCoin(player.getCoin() + coin);
                playerRepository.save(player);
        });
    }

    public boolean buySkin(User user, Skin skin){
        return playerRepository.findByUser(user).map(player -> {
            Long coin = player.getCoin();
            if (coin > skin.getCost() && !player.getPlayerSkins().contains(skin)) {
                player.setCoin(player.getCoin() - skin.getCost());
                player.getPlayerSkins().add(skin);
                playerRepository.save(player);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public Optional<Player> getPlayer(User user){
        return playerRepository.findByUser(user);
    }

    public void createNewPlayer(User user){
        Player player = new Player();
        player.setUser(user);
        player.setCoin(0L);
        player.setPlayerSkins(List.of(skinService.getStandartSkin().get()));
        playerRepository.save(player);
    }
}
