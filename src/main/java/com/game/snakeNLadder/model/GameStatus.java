package com.game.snakeNLadder.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameStatus {
    Dice dice1;
    Dice dice2;
    Player currentPlayer;
    Player nextPlayer;
    Boolean gameEndFlag;

    @Override
    public String toString() {
        return "GameStatus{" +
                "dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", currentPlayer.currentPosition=" + currentPlayer.getCurrentPosition() +
                ", gameEndFlag=" + gameEndFlag +
                '}';
    }
}
