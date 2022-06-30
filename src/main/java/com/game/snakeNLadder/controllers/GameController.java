package com.game.snakeNLadder.controllers;

import com.game.snakeNLadder.model.*;
import com.game.snakeNLadder.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GameController extends BaseController {

    LinkedHashMap<String, Player> playerMap = new LinkedHashMap<>(4);
    Map<Integer,Snake> snakes = new HashMap<>(4);
    Map<Integer,Ladder> ladders =new HashMap<>(4);
    static GameStatus gameStatus = null;
    static Player currentPlayer = null;

    @Autowired
    GameService gameService;

    @RequestMapping("/")
    public String index()
    {
        resetData();
        return "index";
    }

    @RequestMapping("/play")
    public ModelAndView start()
    {
        ModelAndView mav = new ModelAndView("game");
        currentPlayer = currentPlayer == null ? playerMap.values().stream().findFirst().get() : currentPlayer;
        currentPlayer.setPlayStatusFlag(true);
        GameStatus gameStatus = new GameStatus(null, null
                , currentPlayer, null, false);
        mav.addObject("players", playerMap.values());
        mav.addObject("gameStatus", gameStatus);
        return mav;
    }

    @PostMapping("/initiate")
    public ResponseEntity initiate(@RequestBody (required = false) List<String> playerNames)
    {
        gameService.createPlayers(playerNames, playerMap);
        gameService.initiateSnakes(snakes);
        gameService.initiateLadders(ladders);
        currentPlayer = playerMap.values().stream().findFirst().get();
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/restart")
    public ModelAndView restart()
    {
        ModelAndView mav = new ModelAndView("game");
        gameService.initiateSnakes(snakes);
        gameService.initiateLadders(ladders);
        clearPlayerPositions();
        currentPlayer = playerMap.values().stream().findFirst().get();
        currentPlayer.setPlayStatusFlag(true);
        gameStatus = new GameStatus(null, null, currentPlayer, null, false);
        mav.addObject("players", playerMap.values());
        mav.addObject("gameStatus", gameStatus);
        return mav;
    }

    @PostMapping("/roll")
    public ModelAndView roll(@RequestParam String id)
    {
        System.out.println("id : " + id);
        ModelAndView mav = new ModelAndView("game");
        Player player = playerMap.get(id);
        Boolean isShootedBack = false;
        if(!player.getPlayStatusFlag()) {
            mav.addObject("gameStatus", gameStatus);
            mav.addObject("players", playerMap.values());
            return mav;
        }
        currentPlayer = player;
        System.out.println("player : " + player);
        List<Dice> rolledDices = gameService.rollDices();
        Integer totalMoves = rolledDices
                                .stream()
                                .collect(Collectors
                                            .summingInt(Dice :: getCurrentValue));
        System.out.println("totalMoves : " + totalMoves + "rolledDices : " + rolledDices);
        Integer newPosition = player.getCurrentPosition() + totalMoves;
        player.setPlayStatusFlag(false);
        Boolean gameEndStatusFlag = false;
        if(newPosition == 100) {
            gameEndStatusFlag = true;
            player = gameService.movePosition(player, newPosition);
        } else if(newPosition > 100) {
            player = gameService.shootBack(player, newPosition);
            newPosition = player.getCurrentPosition();
            isShootedBack = true;
        }

        if(!gameEndStatusFlag) {
            if (gameService.isSnakeBites(snakes, newPosition)) {
                player = gameService.moveToSnakeTailPosition(player, newPosition, snakes);
            } else if (gameService.hasLadderAHead(ladders, newPosition)) {
                player = gameService.moveToLadderHeadPosition(player, newPosition, ladders);
            } else if (!isShootedBack) {
                player = gameService.movePosition(player, newPosition);
            }
            if(!gameService.rolledDicesAreDoubles(rolledDices)) {
                player.getNextPlayer().setPlayStatusFlag(true);
                playerMap.put(player.getNextPlayer().getId(), player.getNextPlayer());
            } else {
                player.setPlayStatusFlag(true);
            }
        }

        playerMap.put(player.getId(), player);
        gameStatus = new GameStatus(rolledDices.get(0), rolledDices.get(1)
                , player, player.getNextPlayer(), gameEndStatusFlag);
        System.out.println("gameStatus after play: " + gameStatus);
        mav.addObject("gameStatus", gameStatus);
        mav.addObject("players", playerMap.values());
        return mav;
    }

    private void resetData() {
        playerMap = new LinkedHashMap<>(4);
        ladders = new HashMap<>(4);
        snakes = new HashMap<>(4);
        gameStatus = null;
        currentPlayer = null;
    }

    private void clearPlayerPositions() {
        for (Player p : playerMap.values()) {
            p.setCurrentPosition(0);
            p.setPreviousPosition(0);
        }
    }
}
