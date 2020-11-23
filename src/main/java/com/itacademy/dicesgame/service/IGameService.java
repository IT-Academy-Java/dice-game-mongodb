package com.itacademy.dicesgame.service;

import com.itacademy.dicesgame.entity.Game;

import java.util.List;

public interface IGameService {

    public Game rollDices(Long player_id);
    public void deleteByPlayerId(Long player_id);
    public List<Game> getGamesByPlayer(Long player_id);

}
