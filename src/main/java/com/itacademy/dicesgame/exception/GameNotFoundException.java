package com.itacademy.dicesgame.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String msg){
        super(msg);
    }
}
