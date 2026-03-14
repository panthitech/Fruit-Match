package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.handler;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;


public interface SpecialTileHandler {

    void handleSpecialTile(Tile[][] tiles, Tile tile, int row, int col);

}
