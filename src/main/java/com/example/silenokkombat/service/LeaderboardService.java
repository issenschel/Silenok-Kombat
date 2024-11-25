package com.example.silenokkombat.service;

import com.example.silenokkombat.dto.LeaderboardDto;
import com.example.silenokkombat.entity.Leaderboard;
import com.example.silenokkombat.entity.Player;
import com.example.silenokkombat.repository.LeaderboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class LeaderboardService {
    private final PlayerService playerService;
    private final LeaderboardRepository leaderboardRepository;

    @Transactional
    @Scheduled(initialDelay = 10000,fixedRate = 60000)
    public void setLeaderboard(){
        List<Leaderboard> leaderboards = leaderboardRepository.findAll();
        List<Player> players = playerService.getPlayersForLeaderboard();
        IntStream.range(0, 10)
                    .forEach(i -> {
                        Player player = players.get(i);
                        Leaderboard leaderboard = leaderboards.get(i);
                        leaderboard.setPlayer(player);
                        leaderboards.set(i, leaderboard);
                    });
        leaderboardRepository.saveAll(leaderboards);
    }

    public List<LeaderboardDto> getLeaderboard(){
            return leaderboardRepository.findAllPlayers();
    }
}
