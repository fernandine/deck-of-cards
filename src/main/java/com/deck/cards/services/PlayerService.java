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

import java.util.Collections;
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

    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        List<Player> list = repository.findAll();
        return list.stream()
                .map(entity -> modelMapper.map(entity, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PlayerDto> distributeCards() {
        List<PlayerDto> players = findAll();

        if (players.size() > 4) {
            players = players.subList(0, 4);
        } else if (players.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum jogador encontrado.");
        }

        // Cria o baralho e embaralha.
        DeckDto response = apiDeckOfCards.newDeck(true);
        apiDeckOfCards.shuffle(1);

        PlayerDto winner = null;
        int maxScore = -1;

        for (PlayerDto player : players) {
            DeckDto drawResult = apiDeckOfCards.drawCard(response.getDeckId(), 5);
            List<CardDto> dtos = drawResult.getCards();

            player.setCards(dtos);
            player.winner(players);

            int playerScore = player.winner(players);

            if (playerScore > maxScore) {
                maxScore = playerScore;
                winner = player;
            } else if (playerScore == maxScore) {
                winner = null;
            }

            Player entity = modelMapper.map(player, Player.class);
            repository.save(entity);
        }

        if (winner != null) {
            return Collections.singletonList(winner);
        } else {
            return players;
        }
    }

}


