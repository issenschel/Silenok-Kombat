package com.example.silenokkombat.repository;

import com.example.silenokkombat.entity.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Byte> {
        Optional<Skin> findByName(String name);
}
