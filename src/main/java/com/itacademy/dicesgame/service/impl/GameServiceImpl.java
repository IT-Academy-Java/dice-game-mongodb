package com.itacademy.dicesgame.service.impl;

import com.itacademy.dicesgame.entity.Game;
import com.itacademy.dicesgame.entity.Player;
import com.itacademy.dicesgame.exception.PlayerNotFoundException;
import com.itacademy.dicesgame.repository.IGameRepository;
import com.itacademy.dicesgame.repository.IPlayerRepository;
import com.itacademy.dicesgame.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public Game rollDices(Long player_id) {
        if(playerRepository.findById(player_id) != null){
            try {
                Game game = new Game(playerRepository.findById(player_id));
                game.rollDices();
                return gameRepository.save(game);
            } catch (Exception e){
                System.out.println("Error -> " + e.getMessage());
            }
        } else{
            throw new PlayerNotFoundException("Player with id: " + player_id + " does not exist!!!");
        }
        return null;
    }

    @Override
    public void deleteByPlayerId(Long player_id) {
        Player player = playerRepository.findById(player_id);
        try {
            gameRepository.deleteByPlayer(player);
        } catch (Exception e){
            System.out.println("Error -> " + e.getMessage());
        }
    }

    @Override
    public List<Game> getGamesByPlayer(Long player_id) {
        return gameRepository.getGamesByPlayerId(player_id);
    }
}
