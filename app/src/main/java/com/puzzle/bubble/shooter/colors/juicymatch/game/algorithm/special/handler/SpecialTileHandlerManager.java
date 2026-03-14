package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.handler;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;

import java.util.HashMap;
import java.util.Map;


public class SpecialTileHandlerManager {

    private final Map<SpecialType, SpecialTileHandler> mSpecialTileHandlers = new HashMap<>();

    public SpecialTileHandlerManager(Engine engine) {
        // Add all the special tile handler
        mSpecialTileHandlers.put(SpecialType.ICE_CREAM, new IceCreamHandler(engine));
        mSpecialTileHandlers.put(SpecialType.EXPLOSIVE, new ExplosiveTileHandler(engine));
        mSpecialTileHandlers.put(SpecialType.ROW_STRIPED, new RowStripedTileHandler(engine));
        mSpecialTileHandlers.put(SpecialType.COLUMN_STRIPED, new ColumnStripedTileHandler(engine));
    }
    public void checkSpecialTile(Tile[][] tiles, Tile tile, int row, int col) {
        SpecialType type = tile.getSpecialType();
        SpecialTileHandler handler = mSpecialTileHandlers.get(type);
        if (handler != null) {
            handler.handleSpecialTile(tiles, tile, row, col);
        }
    }

}
