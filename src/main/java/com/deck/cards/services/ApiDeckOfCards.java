package com.deck.cards.services;

import com.deck.cards.Dtos.DeckDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="https://deckofcardsapi.com/api/deck/", name="deckofcards")
public interface ApiDeckOfCards {

    //Um baralho totalmente novo
    @PostMapping("new/")
    DeckDto newDeck(boolean jokers_enabled);

    //Embaralhar as cartas
    @GetMapping("{deck_id}/shuffle/")
    DeckDto shuffle(@PathVariable("deck_id") String deckId);

    //Compre uma carta
    @GetMapping("{deck_id}/draw/")
    DeckDto drawCard(@PathVariable("deck_id") String deckId, @RequestParam("count") int count);


}
