package com.game.snakeNLadder.services;

import com.game.snakeNLadder.model.Dice;
import com.game.snakeNLadder.model.Ladder;
import com.game.snakeNLadder.model.Player;
import com.game.snakeNLadder.model.Snake;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService{

    Integer diceStartRange = 1;
    Integer diceEndRange = 6;

    public Integer roll() {
        return (int) ((Math.random() * (diceEndRange - diceStartRange)) + diceStartRange);
    }

    public List<Dice> rollDices() {
        Integer dice1Value = roll();
        Integer dice2Value = roll();
        Dice dice1 = new Dice(1, dice1Value);
        Dice dice2 = new Dice(2, dice2Value);
        return new ArrayList<>(Arrays.asList(dice1, dice2));
    }
    public Player shootBack(Player player, Integer newPosition) {
        Integer totalBackMoves = newPosition - 100;
        newPosition = 100 - totalBackMoves;
        player.setPreviousPosition(player.getCurrentPosition());
        player.setCurrentPosition(newPosition);
        return player;
    }
    public Boolean rolledDicesAreDoubles(List<Dice> rolledDices) {
        return rolledDices.get(0).getCurrentValue() == rolledDices.get(1).getCurrentValue();
    }

    public Player movePosition(Player player,Integer newPosition) {
        Integer targetPosition = newPosition;
        player.setPreviousPosition(player.getCurrentPosition());
        player.setCurrentPosition(targetPosition);
        return player;
    }

    public Player moveToSnakeTailPosition(Player player, Integer newPosition, Map<Integer, Snake> snakes) {
        Integer snakeTailPosition = snakes.get(newPosition).getTail();
        player.setPreviousPosition(player.getCurrentPosition());
        player.setCurrentPosition(snakeTailPosition);
        return player;
    }

    public Player moveToLadderHeadPosition(Player player, Integer newPosition, Map<Integer, Ladder> ladders) {
        Integer ladderHeadPosition = ladders.get(newPosition).getTop();
        player.setPreviousPosition(player.getCurrentPosition());
        player.setCurrentPosition(ladderHeadPosition);
        System.out.println("player after ladder: " + player);
        return player;
    }

    public Boolean isSnakeBites(Map<Integer, Snake> snakes, Integer newPosition) {
        return snakes.containsKey(newPosition);
    }

    public Boolean hasLadderAHead(Map<Integer, Ladder> ladders, Integer newPosition) {
        return ladders.containsKey(newPosition);
    }

    public Map<String, Player> createPlayers(List<String> players, Map<String, Player> playerMap) {
        Player player1 = new Player(players.get(0));
        Player player2 = new Player(players.get(1));
        Player player3 = new Player(players.get(2));
        Player player4 = new Player(players.get(3));
        player1.setNextPlayer(player2);
        player2.setNextPlayer(player3);
        player3.setNextPlayer(player4);
        player4.setNextPlayer(player1);
        playerMap.put(player1.getId(), player1);
        playerMap.put(player2.getId(), player2);
        playerMap.put(player3.getId(), player3);
        playerMap.put(player4.getId(), player4);
        return playerMap;
    }

    @Override
    public Map<Integer, Snake> initiateSnakes(Map<Integer, Snake> snakes) {
        Snake snake1 = new Snake(16, 6);
        Snake snake2 = new Snake(46, 25);
        Snake snake3 = new Snake(49, 11);
        Snake snake4 = new Snake(62, 19);
        Snake snake5 = new Snake(64, 60);
        Snake snake6 = new Snake(74, 53);
        Snake snake7 = new Snake(89, 68);
        Snake snake8 = new Snake(92, 88);
        Snake snake9 = new Snake(95, 75);
        Snake snake10 = new Snake(99, 80);
        snakes.put(snake1.getHead(), snake1);
        snakes.put(snake2.getHead(), snake2);
        snakes.put(snake3.getHead(), snake3);
        snakes.put(snake4.getHead(), snake4);
        snakes.put(snake5.getHead(), snake5);
        snakes.put(snake6.getHead(), snake6);
        snakes.put(snake7.getHead(), snake7);
        snakes.put(snake8.getHead(), snake8);
        snakes.put(snake9.getHead(), snake9);
        snakes.put(snake10.getHead(), snake10);
        return snakes;
    }

    @Override
    public Map<Integer, Ladder> initiateLadders(Map<Integer, Ladder> ladders) {
        Ladder ladder1 = new Ladder(2, 38);
        Ladder ladder2 = new Ladder(7, 14);
        Ladder ladder3 = new Ladder(8, 31);
        Ladder ladder4 = new Ladder(15, 26);
        Ladder ladder5 = new Ladder(21, 42);
        Ladder ladder6 = new Ladder(28, 84);
        Ladder ladder7 = new Ladder(36, 44);
        Ladder ladder8 = new Ladder(51, 67);
        Ladder ladder9 = new Ladder(71, 91);
        Ladder ladder10 = new Ladder(78, 98);
        Ladder ladder11 = new Ladder(87, 94);
        ladders.put(ladder1.getBottom(), ladder1);
        ladders.put(ladder2.getBottom(), ladder2);
        ladders.put(ladder3.getBottom(), ladder3);
        ladders.put(ladder4.getBottom(), ladder4);
        ladders.put(ladder5.getBottom(), ladder5);
        ladders.put(ladder6.getBottom(), ladder6);
        ladders.put(ladder7.getBottom(), ladder7);
        ladders.put(ladder8.getBottom(), ladder8);
        ladders.put(ladder9.getBottom(), ladder9);
        ladders.put(ladder10.getBottom(), ladder10);
        ladders.put(ladder11.getBottom(), ladder11);
        return ladders;
    }

    public Map<String, Player> clearPlayerPositions(Map<String, Player> playerMap) {
        for (Player p : playerMap.values()) {
            p.setCurrentPosition(0);
            p.setPreviousPosition(0);
        }
        return playerMap;
    }
}
