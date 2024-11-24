package com.example.silenokkombat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "coin", nullable = false, unique = false)
    private Long coin;

    @OneToMany(mappedBy = "player")
    private Collection<PlayerSkin> playerSkins;
}
