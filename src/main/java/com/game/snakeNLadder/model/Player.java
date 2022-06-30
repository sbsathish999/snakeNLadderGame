package com.game.snakeNLadder.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Player {
    String id;
    String name;
    Integer previousPosition;
    Integer currentPosition;
    Boolean playStatusFlag;
    Player nextPlayer;

    public Player() {};

    public Player(String id, String name, Integer previousPosition, Integer currentPosition, Boolean playStatusFlag, Player nextPlayer) {
        this.id = id;
        this.name = name;
        this.previousPosition = previousPosition;
        this.currentPosition = currentPosition;
        this.playStatusFlag = playStatusFlag;
        this.nextPlayer = nextPlayer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", previousPosition=" + previousPosition +
                ", currentPosition=" + currentPosition +
                ", playStatusFlag=" + playStatusFlag +
                '}';
    }

    public Player(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        previousPosition = 0;
        currentPosition = 0;
        playStatusFlag = false;
        nextPlayer = null;
    }
}
