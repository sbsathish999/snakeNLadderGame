package com.game.snakeNLadder.services;

import com.game.snakeNLadder.model.Dice;
import com.game.snakeNLadder.model.Ladder;
import com.game.snakeNLadder.model.Player;
import com.game.snakeNLadder.model.Snake;

import java.util.List;
import java.util.Map;

public interface GameService {
    List<Dice> rollDices();
    Player shootBack(Player player, Integer newPosition);
    Boolean rolledDicesAreDoubles(List<Dice> rolledDices);
    Player movePosition(Player player, Integer newPosition) ;
    Player moveToSnakeTailPosition(Player player, Integer newPosition, Map<Integer, Snake> snakes);
    Player moveToLadderHeadPosition(Player player, Integer newPosition, Map<Integer, Ladder> ladders);
    Boolean isSnakeBites(Map<Integer, Snake> snakes, Integer newPosition);
    Boolean hasLadderAHead(Map<Integer, Ladder> ladders, Integer newPosition);
    Map<String, Player> createPlayers(List<String> players, Map<String, Player> playerMap);
    Map<Integer, Snake> initiateSnakes(Map<Integer, Snake> snakes);
    Map<Integer, Ladder> initiateLadders(Map<Integer, Ladder> ladders);

    Map<String, Player> clearPlayerPositions(Map<String, Player> playerMap);
}
