package com.example.silenokkombat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skins")
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Byte id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "cost", nullable = false)
    private Integer cost;

}
