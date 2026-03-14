package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type.CandyTile;


public class CandyTargetHandler implements TargetHandler {

    @Override
    public boolean checkTarget(Tile tile) {
        if (tile instanceof CandyTile) {
            CandyTile candy = ((CandyTile) tile);
            return candy.isObstacle() && candy.getObstacleLayer() == 1;
        }
        return false;
    }

}
