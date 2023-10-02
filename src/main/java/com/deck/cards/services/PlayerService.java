package com.deck.cards.services;

import com.deck.cards.Dtos.CardDto;
import com.deck.cards.Dtos.DeckDto;
import com.deck.cards.Dtos.PlayerDto;
import com.deck.cards.entities.Player;
import com.deck.cards.repositories.PlayerRepository;
import com.deck.cards.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private ApiDeckOfCards apiDeckOfCards;
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DeckService deckService;

    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        List<Player> list = repository.findAll();
        return list.stream()
                .map(entity -> modelMapper.map(entity, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public PlayerDto insert(PlayerDto dto) {
        Player player = modelMapper.map(dto, Player.class);
        player = repository.save(player);
        return modelMapper.map(player, PlayerDto.class);
    }

    @Transactional
    public List<PlayerDto> distributeCards() {
        List<PlayerDto> players = findAll();

        if (players.size() > 4) {
            players = players.subList(0, 4);
        } else if (players.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum jogador encontrado.");
        }

        //Cria e embaralha um novo baralho
        DeckDto deck = deckService.createDeck(true);
        deckService.shuffleDeck(deck.getDeckId());

        for (PlayerDto playerDto : players) {
            //Cria 5 cartas para cada jogador
            List<CardDto> dtos = deckService.drawCards(deck.getDeckId(), 5);
            playerDto.setCards(dtos);
            playerDto.winner(players);

            insert(playerDto);
        }

        return players;
    }
}


