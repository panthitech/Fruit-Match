package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.finder;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;


public class SpecialTileFinderManager {

    private final List<SpecialTileFinder> mSpecialTileFinders = new ArrayList<>();

    public SpecialTileFinderManager(Engine engine) {
        // Add all the special tile finder
        mSpecialTileFinders.add(new IceCreamFinder(engine));
        mSpecialTileFinders.add(new ExplosiveTileXFinder(engine));
        mSpecialTileFinders.add(new ExplosiveTileTFinder(engine));
        mSpecialTileFinders.add(new ExplosiveTileLFinder(engine));
        mSpecialTileFinders.add(new RowStripedTileFinder(engine));
        mSpecialTileFinders.add(new ColumnStripedTileFinder(engine));
    }
    public void findSpecialTile(Tile[][] tiles, int row, int col) {
        int size = mSpecialTileFinders.size();
        for (int i = 0; i < size; i++) {
            SpecialTileFinder finder = mSpecialTileFinders.get(i);
            finder.findSpecialTile(tiles, row, col);
        }
    }

}
