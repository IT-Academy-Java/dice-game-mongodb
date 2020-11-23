package com.itacademy.dicesgame.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String msg){
        super(msg);
    }
}
