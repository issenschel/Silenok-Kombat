package com.example.silenokkombat.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Byte id;

    @Column(name = "name", nullable = false,unique = true, length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}
