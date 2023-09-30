package com.deck.cards.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeckDto implements Serializable {

    @JsonProperty("deck_id")
    private String deckId;
    private boolean success;
    private int remaining;
    private boolean shuffled;

    private List<CardDto> cards = new ArrayList<>();

}
