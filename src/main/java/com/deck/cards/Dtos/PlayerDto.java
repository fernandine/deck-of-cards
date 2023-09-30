package com.deck.cards.Dtos;

import com.deck.cards.entities.Player;
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
public class PlayerDto implements Serializable {

    private Long id;
    private String name;
    private Boolean winner;
    private int total;
    private List<CardDto> cards = new ArrayList<>();

    public void winner(List<PlayerDto> players) {
        int maxSum = 0;

        for (PlayerDto player : players) {
            int cardSum = 0;
            for (CardDto card : player.getCards()) {
                cardSum += card.getValueCard();
            }
            player.setTotal(cardSum);

            maxSum = Math.max(maxSum, cardSum);
        }
        for (PlayerDto player : players) {
            player.setWinner(player.getTotal() == maxSum);
        }
    }
}
