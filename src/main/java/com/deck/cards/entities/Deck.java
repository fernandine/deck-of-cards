package com.deck.cards.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deck implements Serializable {

    private String deck_id;
    private boolean success;
    private int remaining;
    private boolean shuffled;

}
