package com.deck.cards.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String code;

    private String value;
    private String suit;

    public int calculateHandScore(List<CardDto> cards) {
        int score = 0;
        for (CardDto card : cards) {
            String value = card.getValue();
            if ("ACE".equals(value)) {
                score += 1;
            } else if ("KING".equals(value)) {
                score += 13;
            } else if ("QUEEN".equals(value)) {
                score += 12;
            } else if ("JACK".equals(value)) {
                score += 11;
            } else {
                score += Integer.parseInt(value);
            }
        }
        return score;
    }
}

