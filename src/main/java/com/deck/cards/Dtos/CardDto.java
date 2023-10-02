package com.deck.cards.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String code;
    @NotBlank
    private String value;
    private String suit;

    public int getValueCard() {
        switch (value) {
            case "ACE":
                return 1;
            case "KING":
                return 13;
            case "QUEEN":
                return 12;
            case "JACK":
                return 11;
            default:
                return Integer.parseInt(value);
        }
    }
}

