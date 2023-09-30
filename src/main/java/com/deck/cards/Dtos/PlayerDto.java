package com.deck.cards.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto implements Serializable {

    private Long id;
    @NotBlank
    private Boolean winner;

    @NotBlank
    private int CardSum;

    private List<CardDto> cards = new ArrayList<>();

    public int winner(List<PlayerDto> players) {
        int maxSum = 0;

        for (PlayerDto player : players) {
            int cardSum = 0;
            for (CardDto card : player.getCards()) {
                cardSum += card.calculateHandScore(cards);
            }
            player.setCardSum(cardSum);

            maxSum = Math.max(maxSum, cardSum);
        }

        for (PlayerDto player : players) {
            player.setWinner(player.getCardSum() == maxSum);
        }
        return maxSum;
    }

}
