package com.deck.cards.entities;

import com.deck.cards.Dtos.CardDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tb_cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends CardDto implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private String code;
    @Column(name = "tb_value")
    private String value;
    private String suit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    private Player player;


}
