package com.deck.cards.Dtos;

import com.deck.cards.entities.Cards;
import com.deck.cards.entities.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
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

    public void winner(List<PlayerDto> players) {
        int maxSum = 0;

        for (PlayerDto player : players) {
            int cardSum = 0;
            for (CardDto card : player.getCards()) {
                cardSum += card.getValueCard();
            }
            player.setCardSum(cardSum);

            maxSum = Math.max(maxSum, cardSum);
        }

        for (PlayerDto player : players) {
            player.setWinner(player.getCardSum() == maxSum);
        }
    }

}
