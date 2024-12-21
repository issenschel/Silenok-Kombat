package com.example.silenokkombat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaderboardDto {
    private Long id;
    private String name;
    private Long count;
}
