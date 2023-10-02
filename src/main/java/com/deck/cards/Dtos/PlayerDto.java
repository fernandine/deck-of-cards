package com.deck.cards.Dtos;

import com.deck.cards.entities.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto implements Serializable {

    @JsonIgnore
    private Long id;
    @NotBlank
    private String name;
    private Boolean winner;
    private Integer points;
    private List<CardDto> cards = new ArrayList<>();

    public void winner(List<PlayerDto> players) {
        int maxSum = 0;

        for (PlayerDto player : players) {
            int cardSum = 0;
            for (CardDto card : player.getCards()) {
                cardSum += card.getValueCard();
            }
            player.setPoints(cardSum);

            maxSum = Math.max(maxSum, cardSum);
        }
        for (PlayerDto player : players) {
            player.setWinner(player.getPoints() == maxSum);
        }
    }
}
