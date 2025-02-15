package com.example.silenokkombat.service;

import com.example.silenokkombat.entity.Role;
import com.example.silenokkombat.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){
        return roleRepository.findByName("ROLE_USER").get();
    }
}
