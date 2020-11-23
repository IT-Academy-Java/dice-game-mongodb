package com.itacademy.dicesgame.controller;

import com.itacademy.dicesgame.entity.Player;
import com.itacademy.dicesgame.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
public class PlayerController {

    @Autowired
    private PlayerServiceImpl service;

    @GetMapping()
    @ResponseBody
    public List<Player> getPlayers() throws Exception{
        return service.getAllPlayers();
    }

    @GetMapping("/")
    @ResponseBody
    public Map<String, Double> getPlayersWithAvgSuccessRate() throws Exception{
        return service.getAllPlayersWithAvgSuccessRate();
    }

    @GetMapping("/ranking")
    @ResponseBody
    public List<Player> getPlayersRanking() throws Exception{
        return service.getPlayersRanking();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Player getPlayerById(@PathVariable(value = "id") Long id) throws Exception{
        return service.findPlayer(id);
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        return ResponseEntity.ok().body(service.savePlayer(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayer(
            @RequestBody Player player,
            @PathVariable("id") Long id
            ){

        return ResponseEntity.ok().body(service.updatePlayer(id, player));
    }

    @GetMapping("/ranking/loser")
    @ResponseBody
    public Player getLoserPlayer() throws Exception{
        return service.getLoserPlayer();
    }

    @GetMapping("/ranking/winner")
    @ResponseBody
    public Player getWinPlayer() throws Exception{
        return service.getWinPlayer();
    }

}
