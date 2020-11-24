package com.itacademy.dicesgame.repository;

import com.itacademy.dicesgame.entity.Game;
import com.itacademy.dicesgame.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IGameRepository extends MongoRepository<Game, Long> {

    void deleteByPlayer(Player player);

    List<Game> getGamesByPlayerId(String player_id);
}

