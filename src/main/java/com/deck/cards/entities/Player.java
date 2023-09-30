package com.deck.cards.entities;

import com.deck.cards.Dtos.CardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "tb_player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean winner;
    @Column(name = "card_sum")
    private int CardSum;

    @OneToMany(mappedBy = "player")
    private List<Cards> cards;

}
