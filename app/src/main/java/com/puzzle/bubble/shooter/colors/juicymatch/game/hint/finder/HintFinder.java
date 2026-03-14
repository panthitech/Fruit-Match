package com.puzzle.bubble.shooter.colors.juicymatch.game.hint.finder;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;

import java.util.List;


public interface HintFinder {

    List<Tile> findHint(Tile[][] tiles, int row, int col);

}
