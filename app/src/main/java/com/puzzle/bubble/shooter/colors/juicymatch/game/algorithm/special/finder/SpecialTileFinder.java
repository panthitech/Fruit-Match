package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.finder;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;


public interface SpecialTileFinder {

    void findSpecialTile(Tile[][] tiles, int row, int col);

}
