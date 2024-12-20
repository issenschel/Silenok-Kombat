package com.example.silenokkombat.repository;

import com.example.silenokkombat.entity.UserToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken,Long> {
    Optional<UserToken> findByVersionToken(String token);
}
