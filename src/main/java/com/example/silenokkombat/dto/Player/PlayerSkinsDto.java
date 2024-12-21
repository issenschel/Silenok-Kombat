package com.example.silenokkombat.dto.Player;

import com.example.silenokkombat.entity.Skin;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class PlayerSkinsDto {
    private Collection<Skin> playerSkins;
}

