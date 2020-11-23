package com.itacademy.dicesgame.service.impl;

import com.itacademy.dicesgame.entity.Game;
import com.itacademy.dicesgame.entity.Player;
import com.itacademy.dicesgame.exception.PlayerNameAlreadyExistsException;
import com.itacademy.dicesgame.exception.PlayerNotFoundException;
import com.itacademy.dicesgame.repository.IGameRepository;
import com.itacademy.dicesgame.repository.IPlayerRepository;
import com.itacademy.dicesgame.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private IGameRepository gameRepository;

    @Override
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @Override
    public Map<String, Double> getAllPlayersWithAvgSuccessRate() {
        List<Player> listAllPlayers = playerRepository.findAll();
        Map<String, Double> mapPlayersWithAvgSuccessRate = new HashMap<String, Double>();

        if(listAllPlayers != null && listAllPlayers.size() > 0){
            List<Game> gamesofActualPlayer;

            for(Player player: listAllPlayers){
                gamesofActualPlayer = gameRepository.getGamesByPlayerId(player.getId());

                if(gamesofActualPlayer.size() > 0){
                    String key = player.getName();
                    Double value = player.getSuccessRateByPlayer(gameRepository.getGamesByPlayerId(player.getId()));
                    System.out.println();
                    mapPlayersWithAvgSuccessRate.put(key,value);

                } else{
                    mapPlayersWithAvgSuccessRate.put(player.getName(), (double) 0) ;
                }
            }
        }
        return mapPlayersWithAvgSuccessRate;
    }

    @Override
    public List<Player> getPlayersRanking() {
        List<Player> listAllPlayers = playerRepository.findAll();
        List<Game> listOfGamesActualPlayer = new ArrayList<Game>();

        if(listAllPlayers != null && listAllPlayers.size() > 0){
            try {
                for(Player player: listAllPlayers){
                    listOfGamesActualPlayer = gameRepository.getGamesByPlayerId(player.getId());
                    Double successRate = player.getSuccessRateByPlayer(listOfGamesActualPlayer);
                    player.setSuccessRate(successRate.toString());
                }
                listAllPlayers.sort(Comparator.comparing(Player::getSuccessRate).reversed());
            } catch (Exception e){
                System.out.println("Error -> " + e.getMessage());
            }
        }
        return listAllPlayers;
    }

    @Override
    public Player findPlayer(Long player_id){
        if(playerRepository.findById(player_id) != null){
            return playerRepository.findById(player_id);
        } else{
            throw new PlayerNotFoundException("Player with id -> " + player_id + " does not exist!!!");
        }
    }

    @Override
    public Player savePlayer(Player player){
    try{
        if(player.getName() == null || player.getName() == ""){
            player.setName("Anonymous");
            return playerRepository.save(player);
        } else if(playerRepository.existsByName(player.getName())){
           throw new PlayerNameAlreadyExistsException("Player with name: " + player.getName() + " already exists!!!");
        } else{
            return playerRepository.save(player);
        }
    } catch (Exception e){
        System.out.println("Error -->> " + e.getMessage());
    }
        return null;
    }

    @Override
    public Player updatePlayer(Long player_id, Player player){
        try {
            if(playerRepository.findById(player_id) == null || playerRepository.existsByName(player.getName())){
                throw new PlayerNotFoundException("Player with id: " + player_id + " does not exist!!!");
            } else{
                player.setId(player_id);
                player.setRegistration_date(playerRepository.findById(player_id).getRegistration_date());
                return playerRepository.save(player);
            }
        } catch (Exception e){
            System.out.println("Error ->>> " + e.getMessage());
        }
        return null;
    }

    @Override
    public Player getLoserPlayer() {
        List<Player> listAllPlayers = getListPlayerWithRate();
        listAllPlayers.sort(Comparator.comparing(Player::getSuccessRate));
        return listAllPlayers.get(0);
    }

    @Override
    public Player getWinPlayer() {
        List<Player> listAllPlayers = getListPlayerWithRate();
        listAllPlayers.sort(Comparator.comparing(Player::getSuccessRate).reversed());
        return listAllPlayers.get(0);
    }

    public List<Player> getListPlayerWithRate(){
        List<Player> listAllPlayers = playerRepository.findAll();
        List<Game> listOfGamesActualPlayer = new ArrayList<Game>();

        if(listAllPlayers != null && listAllPlayers.size() > 0){
            try {
                for(Player player: listAllPlayers){
                    listOfGamesActualPlayer = gameRepository.getGamesByPlayerId(player.getId());
                    Double successRate = player.getSuccessRateByPlayer(listOfGamesActualPlayer);
                    player.setSuccessRate(successRate.toString());
                }

            } catch (Exception e){
                System.out.println("Error -> " + e.getMessage());
            }
        }

        return listAllPlayers;
    }
}
