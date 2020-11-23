package com.itacademy.dicesgame.controller;

import com.itacademy.dicesgame.entity.Game;
import com.itacademy.dicesgame.exception.PlayerNotFoundException;
import com.itacademy.dicesgame.service.impl.GameServiceImpl;
import com.itacademy.dicesgame.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping("/players")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private PlayerServiceImpl playerService;

    @GetMapping("/{id}/games")
    @ResponseBody
    public List<Game> getGamesByPlayer(@PathVariable(value = "id") Long id) throws Exception{
        try {
            if(id != null && gameService.getGamesByPlayer(id) != null){
                return gameService.getGamesByPlayer(id);
            } else{
                throw new PlayerNotFoundException("Player with id -> " + id + " does not exist!!!");
            }
        } catch (Exception e){
            System.out.println("Error -> " + e.getMessage());
        }
        return null;
    }

    @PostMapping("/{id}/games")
    public ResponseEntity<Game> addRollDices(@PathVariable(value="id") Long id) throws Exception{
        return ResponseEntity.ok().body(gameService.rollDices(id));
    }

    @DeleteMapping("/{id}/games")
    public ResponseEntity deleteRollsByPlayer(@PathVariable(value = "id") Long id) throws Exception{
         try {
             if(id != null && playerService.findPlayer(id) != null){
                 gameService.deleteByPlayerId(id);
                 return ResponseEntity.ok().build();
             } else{
                 throw new PlayerNotFoundException("Player with id -> " + id + " does not exist!!!");
             }

         } catch (Exception e){
             System.out.println("Error ==>> " + e.getMessage());
         }
         return null;
    }

}
