package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type.CakeTile;


public class CakeTargetHandler implements TargetHandler {

    @Override
    public boolean checkTarget(Tile tile) {
        if (tile instanceof CakeTile) {
            CakeTile cake = ((CakeTile) tile);
            return cake.isObstacle() && cake.getObstacleLayer() == 1;
        }
        return false;
    }

}
