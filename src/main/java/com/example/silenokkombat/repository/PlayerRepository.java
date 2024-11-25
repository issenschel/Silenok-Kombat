package com.example.silenokkombat.repository;

import com.example.silenokkombat.entity.Player;
import com.example.silenokkombat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByUser(User user);
}
