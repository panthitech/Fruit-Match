package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type.CookieTile;


public class CookieTargetHandler implements TargetHandler {

    @Override
    public boolean checkTarget(Tile tile) {
        if (tile instanceof CookieTile) {
            CookieTile cookie = ((CookieTile) tile);
            return cookie.isObstacle();
        }
        return false;
    }

}
