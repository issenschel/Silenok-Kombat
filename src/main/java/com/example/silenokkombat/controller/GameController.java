package com.example.silenokkombat.controller;

import com.example.silenokkombat.dto.MessageDto;
import com.example.silenokkombat.dto.Player.PlayerCoinDto;
import com.example.silenokkombat.dto.Player.PlayerSkinsDto;
import com.example.silenokkombat.dto.Player.RequestCoinDto;
import com.example.silenokkombat.dto.StatusResponseDto;
import com.example.silenokkombat.entity.Player;
import com.example.silenokkombat.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/getCoin")
    public ResponseEntity<?> getPlayer(){
        Player player = gameService.getPlayer();
        if(player != null){
            return ResponseEntity.ok().body(new PlayerCoinDto(player.getCoin()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDto("Пользователь не найден"));
    }

    @GetMapping("/getSkins")
    public ResponseEntity<?> getSkins(){
        Player player = gameService.getPlayer();
        if(player != null){
            return ResponseEntity.ok().body(new PlayerSkinsDto(player.getPlayerSkins()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDto("Пользователь не найден"));
    }



    @PutMapping("/changeCoin")
    public ResponseEntity<?> changeCoin(@Valid @RequestBody RequestCoinDto requestCoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new MessageDto(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
        }
        StatusResponseDto statusResponseDto = gameService.changeCoin(requestCoinDto.getCoin());
        return ResponseEntity.status(statusResponseDto.getStatus()).body(new MessageDto(statusResponseDto.getMessage()));
    }

    @PutMapping("/buySkin/{id}")
    public ResponseEntity<?> buySkin(@PathVariable Byte id) {
        StatusResponseDto statusResponseDto = gameService.buySkin(id);
        return ResponseEntity.status(statusResponseDto.getStatus()).body(new MessageDto(statusResponseDto.getMessage()));
    }


}
