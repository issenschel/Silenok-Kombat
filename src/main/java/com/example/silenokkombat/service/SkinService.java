package com.example.silenokkombat.service;

import com.example.silenokkombat.entity.Skin;
import com.example.silenokkombat.repository.SkinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkinService {
    private final SkinRepository skinRepository;

    public Optional<Skin> findById(Byte skin){
        return skinRepository.findById(skin);
    }

    public Optional<Skin> getStandartSkin(){
        return skinRepository.findByName("Standart");
    }
}
