package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type.PieTile;


public class PieTargetHandler implements TargetHandler {

    @Override
    public boolean checkTarget(Tile tile) {
        if (tile instanceof PieTile) {
            PieTile pie = ((PieTile) tile);
            return pie.isObstacle() && pie.getObstacleLayer() == 1;
        }
        return false;
    }

}
