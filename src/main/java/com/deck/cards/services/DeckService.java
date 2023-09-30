package com.deck.cards.services;

import com.deck.cards.Dtos.CardDto;
import com.deck.cards.Dtos.DeckDto;

import java.util.List;

public interface DeckService {
    DeckDto createDeck(boolean includeJokers);
    DeckDto shuffleDeck(String deckId);
    List<CardDto> drawCards(String deckId, int count);
}
