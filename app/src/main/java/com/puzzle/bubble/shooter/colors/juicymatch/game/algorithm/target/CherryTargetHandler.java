package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;


public class CherryTargetHandler implements TargetHandler {

    @Override
    public boolean checkTarget(Tile tile) {
        return tile.getTileType() == FruitType.CHERRY;
    }

}
