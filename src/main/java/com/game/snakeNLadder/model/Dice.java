package com.game.snakeNLadder.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dice {
    Integer id;
    Integer currentValue;
}
