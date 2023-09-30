package com.deck.cards.controllers;

import com.deck.cards.Dtos.PlayerDto;
import com.deck.cards.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping
    public ResponseEntity<List<PlayerDto>> findAll() {
        List<PlayerDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

        @PostMapping("/distribute-cards")
    public ResponseEntity<List<PlayerDto>> playCardGame() {
        List<PlayerDto> newDto = service.distributeCards();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.get(0).getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }
}




