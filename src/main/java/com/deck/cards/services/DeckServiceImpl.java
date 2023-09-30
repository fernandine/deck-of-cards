package com.deck.cards.services;

import com.deck.cards.Dtos.CardDto;
import com.deck.cards.Dtos.DeckDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements DeckService {
    @Autowired
    private ApiDeckOfCards apiDeckOfCards;

    @Override
    public DeckDto createDeck(boolean includeJokers) {
        return apiDeckOfCards.newDeck(includeJokers);
    }

    @Override
    public DeckDto shuffleDeck(String deckId) {
        return apiDeckOfCards.shuffle(deckId);
    }

    @Override
    public List<CardDto> drawCards(String deckId, int count) {
        DeckDto drawResult = apiDeckOfCards.drawCard(deckId, count);
        return drawResult.getCards();
    }
}
