package com.itacademy.dicesgame.service;

import com.itacademy.dicesgame.entity.Game;

import java.util.List;

public interface IGameService {

    public Game rollDices(String player_id);

    public void deleteByPlayerId(String player_id);

    public List<Game> getGamesByPlayer(String player_id);

}
