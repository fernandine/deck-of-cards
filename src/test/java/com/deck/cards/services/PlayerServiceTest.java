package com.deck.cards.services;

import com.deck.cards.Dtos.DeckDto;
import com.deck.cards.Dtos.PlayerDto;
import com.deck.cards.entities.Player;
import com.deck.cards.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@Transactional
public class PlayerServiceTest {

    @Mock
    private PlayerService playerService;
    @Mock
    private DeckService deckService;
    @Mock
    private PlayerRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDistributeCards() {

        List<Player> playerEntities = new ArrayList<>();
        playerEntities.add(new Player());
        when(repository.findAll()).thenReturn(playerEntities); //retornar os jogadores

        DeckDto deckDto = new DeckDto();
        when(deckService.createDeck(false)).thenReturn(deckDto);

        List<PlayerDto> players = playerService.distributeCards();

        //Testa se lista de jogadores retornada não está vazia
        assertNotNull(players);
        assertTrue(players.isEmpty());

        //Verifica se cada jogador tem 5 cartas
        for (PlayerDto playerDto : players) {
            assertNotNull(playerDto.getCards());
            assertEquals(5, playerDto.getCards().size());
        }
    }
}




