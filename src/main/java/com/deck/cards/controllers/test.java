package com.deck.cards.controllers;

import com.deck.cards.Dtos.DeckDto;
import com.deck.cards.services.ApiDeckOfCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mock")
public class test {

    @Autowired
    private ApiDeckOfCards apiDeckOfCards;

    @PostMapping("new")
    public ResponseEntity<DeckDto> find(@RequestParam boolean jokers_enabled) {
        DeckDto dto = apiDeckOfCards.newDeck(jokers_enabled);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("{deck_id}/draw/")
    public ResponseEntity<DeckDto> drawCard(@PathVariable String deck_id, @RequestParam("count") int count) {
        DeckDto dto = apiDeckOfCards.drawCard(deck_id, count);
        return ResponseEntity.ok(dto);
    }
}
