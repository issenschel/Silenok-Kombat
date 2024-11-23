package com.example.silenokkombat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player_skins")
public class PlayerSkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id",referencedColumnName = "id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "skin_id",referencedColumnName = "id", nullable = false)
    private Skin skin;
}
