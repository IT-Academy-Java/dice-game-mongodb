package com.itacademy.dicesgame.exception;

public class PlayerNameAlreadyExistsException extends RuntimeException{
    public PlayerNameAlreadyExistsException(String msg){
        super(msg);
    }
}
