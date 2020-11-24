package com.itacademy.dicesgame.repository;

import com.itacademy.dicesgame.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IPlayerRepository extends MongoRepository<Player, Integer> {

    List<Player> findAll();

    Player findById(String player_id);

    Boolean existsByName(String name);


}
