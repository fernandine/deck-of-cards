package com.deck.cards.controllers;

import com.deck.cards.Dtos.PlayerDto;
import com.deck.cards.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @Operation(summary = "Lista de jogadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador listado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado.")
    })
    @GetMapping
    public ResponseEntity<List<PlayerDto>> findAll() {
        List<PlayerDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @Operation(summary = "Distribui cartas para os jogadores e define o vencedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartas foram criadas, embaralhadas e distribuidas"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Não encontrado.")
    })
        @PostMapping("/distribute-cards")
    public ResponseEntity<List<PlayerDto>> playCardGame() {
        List<PlayerDto> newDto = service.distributeCards();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.get(0).getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PostMapping
    public ResponseEntity<PlayerDto> insert(@RequestBody PlayerDto dto) {
        PlayerDto newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }
}




