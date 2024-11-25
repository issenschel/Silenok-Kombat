package com.example.silenokkombat.repository;

import com.example.silenokkombat.dto.LeaderboardDto;
import com.example.silenokkombat.entity.Leaderboard;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    @Query("SELECT new com.example.silenokkombat.dto.LeaderboardDto(ld.id, u.username, p.coin) " +
            "FROM Leaderboard ld " +
            "JOIN ld.player p " +
            "JOIN p.user u")
    List<LeaderboardDto> findAllPlayers();

    @NonNull
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "player")
    List<Leaderboard> findAll();

}
