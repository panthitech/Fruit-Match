package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.combine;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;


public interface SpecialCombineHandler {

    long getStartDelay();

    boolean checkSpecialCombine(Tile[][] tiles, Tile tileA, Tile tileB, int row, int col);

}
