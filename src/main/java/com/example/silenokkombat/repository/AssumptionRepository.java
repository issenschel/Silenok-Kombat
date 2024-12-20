package com.example.silenokkombat.repository;

import com.example.silenokkombat.entity.Assumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssumptionRepository extends JpaRepository<Assumption, Integer> {
    Optional<Assumption> findByEmail(String email);
}
