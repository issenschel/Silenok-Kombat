package com.example.silenokkombat.repository;

import com.example.silenokkombat.entity.Player;
import com.example.silenokkombat.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByUser(User user);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "leaderboard")
    @Query("SELECT p FROM Player p ORDER BY p.coin DESC LIMIT 10")
    List<Player> findForLeaderboard();
}
