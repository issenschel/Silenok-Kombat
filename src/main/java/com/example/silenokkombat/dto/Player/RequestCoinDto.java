package com.example.silenokkombat.dto.Player;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestCoinDto {
    @NotNull(message = "coin не может быть пустым")
    @Max(value = 1000, message = "Попався... ты как накликал больше 1000")
    private Long coin;
}
