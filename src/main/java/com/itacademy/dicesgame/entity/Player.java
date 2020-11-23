package com.itacademy.dicesgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registration_date;

    @JsonIgnore
    @OneToMany(mappedBy = "player", cascade = {
            CascadeType.ALL
    })
    private List<Game> games;

    @Transient
    private String successRate;

    public Player() {
        this.name = "Anonymous";
    }

    public Player(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registration_date=" + registration_date +
                ", games=" + games +
                '}';
    }

    public double getSuccessRateByPlayer(List<Game> gameList){
        double successRate = 0;
        if(gameList != null && gameList.size() > 0){
            int gameListSizee = gameList.size();
            int totalWin = 0;
            for(Game game: gameList){
                if(game.isWin()){
                    totalWin++;
                }
            }
            successRate = (totalWin * 100) / gameListSizee;
        }
        return successRate;
    }
}
