package com.deck.cards.services;

import com.deck.cards.Dtos.PlayerDto;
import com.deck.cards.repositories.PlayerRepository;
import com.deck.cards.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test //Não há jogadores cadastrados.
    public void testDistributeCardsWithNoPlayers() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> playerService.distributeCards());
    }

    @Test //Verificar se a lista de jogadores retornada está vazia
    public void testDistributeCardsReturnsEmptyListWhenNoPlayersExist() {
        // Simular que não há jogadores cadastrados
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<PlayerDto> result = playerService.distributeCards();
        Assertions.assertEquals(0, result.size());
    }
}

