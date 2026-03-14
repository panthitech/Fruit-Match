package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm;

import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.finder.SpecialTileFinderManager;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;


public abstract class BaseAlgorithm extends Entity implements Algorithm {

    protected final Tile[][] mTiles;
    protected final int mTotalRow;
    protected final int mTotalCol;

    protected final SpecialTileFinderManager mSpecialTileFinder;

    protected BaseAlgorithm(Engine engine, TileSystem tileSystem) {
        super(engine);
        mTiles = tileSystem.getChild();
        mTotalRow = tileSystem.getTotalRow();
        mTotalCol = tileSystem.getTotalColumn();
        mSpecialTileFinder = new SpecialTileFinderManager(engine);
    }

}
