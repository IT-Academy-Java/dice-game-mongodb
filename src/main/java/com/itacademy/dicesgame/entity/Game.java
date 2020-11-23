package com.itacademy.dicesgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dice_value1;
    private int dice_value2;
    private boolean win;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="player_id")
    private Player player;

    /**private Long player_id;*/

    public Game (){}

    public Game(Player player){
        this.player = player;
    }

    public Game(Long id, int dice_value1, int dice_value2, boolean win, Player player) {
        this.id = id;
        this.dice_value1 = dice_value1;
        this.dice_value2 = dice_value2;
        this.win = win;
        this.player = player;
    }

    public int getDice_value1() {
        return dice_value1;
    }

    public void setDice_value1(int dice_value1) {
        this.dice_value1 = dice_value1;
    }

    public int getDice_value2() {
        return dice_value2;
    }

    public void setDice_value2(int dice_value2) {
        this.dice_value2 = dice_value2;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void rollDices(){
        this.dice_value1 = getRandomBetweenOneAndSix();
        this.dice_value2 = getRandomBetweenOneAndSix();
        calculateResult();
    }

    public void calculateResult(){
        if(this.dice_value1 + this.dice_value2 == 7){
            this.win = true;
        } else{
            this.win = false;
        }
    }

    public int getRandomBetweenOneAndSix(){
        int random = (int) Math.floor(Math.random() * 6 + 1);
        return random;
    }
}
