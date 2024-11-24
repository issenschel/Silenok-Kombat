package com.example.silenokkombat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "assumptions")
public class Assumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "code", nullable = false, unique = false, length = 6)
    private String code;
}
