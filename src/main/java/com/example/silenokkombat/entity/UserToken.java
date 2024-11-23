package com.example.silenokkombat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users_token")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "version_token", nullable = false, unique = true, length = 80)
    private String versionToken;

}
