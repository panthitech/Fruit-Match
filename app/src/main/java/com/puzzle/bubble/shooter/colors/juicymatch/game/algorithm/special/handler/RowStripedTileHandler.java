package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.handler;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash.RowFlashEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;


public class RowStripedTileHandler extends BaseSpecialTileHandler {

    private final RowFlashEffectSystem mFlashEffectSystem;

    public RowStripedTileHandler(Engine engine) {
        super(engine);
        mFlashEffectSystem = new RowFlashEffectSystem(engine, 1);
    }
    @Override
    public void handleSpecialTile(Tile[][] tiles, Tile tile, int row, int col) {
        int targetRow = tile.getRow();
        for (int j = 0; j < col; j++) {
            Tile t = tiles[targetRow][j];
            // We make sure not pop the tile multiple time
            if (t.getTileState() == TileState.IDLE) {
                t.popTile();
            }
        }

        playTileEffect(tile);
    }

    @Override
    protected void playTileEffect(Tile tile) {
        super.playTileEffect(tile);
        mFlashEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
    }

}
