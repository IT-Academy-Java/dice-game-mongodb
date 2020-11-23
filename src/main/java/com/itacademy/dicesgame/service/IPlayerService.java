package com.itacademy.dicesgame.service;

import com.itacademy.dicesgame.entity.Player;

import java.util.List;
import java.util.Map;

public interface IPlayerService {

    public List<Player> getAllPlayers();
    public Map<String,Double> getAllPlayersWithAvgSuccessRate();
    public List<Player> getPlayersRanking();
    public Player findPlayer(Long player_id);
    public Player savePlayer(Player player);
    public Player updatePlayer(Long player_id, Player player);
    public Player getLoserPlayer();
    public Player getWinPlayer();
}
