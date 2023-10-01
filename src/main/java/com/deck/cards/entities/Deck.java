package com.deck.cards.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deck implements Serializable {

    @JsonProperty("deck_id")
    private String deckId;
    private boolean success;
    private int remaining;
    private boolean shuffled;
}
