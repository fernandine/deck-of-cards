package com.deck.cards.entities;

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
    private String name;
    private Boolean winner;
    private Integer points;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Cards> cards;

}
