package com.example.silenokkombat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_information")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "email", nullable = false, unique = true, length = 80)
    private String email;

    @Column(name = "photo", nullable = true, unique = false, length = 80)
    private String photo;

}
