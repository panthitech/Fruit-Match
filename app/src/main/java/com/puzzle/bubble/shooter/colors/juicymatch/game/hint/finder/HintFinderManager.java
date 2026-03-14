package com.puzzle.bubble.shooter.colors.juicymatch.game.hint.finder;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;

import java.util.ArrayList;
import java.util.List;


public class HintFinderManager {

    private final List<HintFinder> mHintFinders = new ArrayList<>();

    public HintFinderManager() {
        mHintFinders.add(new SpecialCombineHintFinder());
        mHintFinders.add(new IceCreamHintFinder());
        mHintFinders.add(new ExplosiveStripedTileHintFinder());
        mHintFinders.add(new TileHintFinder());
        mHintFinders.add(new IceCreamTileHintFind());
    }
    public List<Tile> findHint(Tile[][] tiles, int row, int col) {
        int size = mHintFinders.size();
        for (int i = 0; i < size; i++) {
            HintFinder finder = mHintFinders.get(i);
            List<Tile> hintTiles = finder.findHint(tiles, row, col);
            // Check is any hint detected
            if (!hintTiles.isEmpty()) {
                return hintTiles;
            }
        }

        return null;
    }

}
