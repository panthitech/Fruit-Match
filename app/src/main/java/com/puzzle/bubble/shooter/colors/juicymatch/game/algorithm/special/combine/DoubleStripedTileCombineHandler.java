package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.combine;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash.ColumnFlashEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash.RowFlashEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;


public class DoubleStripedTileCombineHandler extends BaseSpecialCombineHandler {

    private final RowFlashEffectSystem mRowFlashEffectSystem;
    private final ColumnFlashEffectSystem mColumnFlashEffectSystem;

    public DoubleStripedTileCombineHandler(Engine engine) {
        super(engine);
        mRowFlashEffectSystem = new RowFlashEffectSystem(engine, 1);
        mColumnFlashEffectSystem = new ColumnFlashEffectSystem(engine, 1);
    }
    @Override
    public long getStartDelay() {
        return 0;
    }

    @Override
    public boolean checkSpecialCombine(Tile[][] tiles, Tile tileA, Tile tileB, int row, int col) {
        // Check are both tiles row or column special tile
        if ((tileA.getSpecialType() == SpecialType.ROW_STRIPED
                || tileA.getSpecialType() == SpecialType.COLUMN_STRIPED)
                && (tileB.getSpecialType() == SpecialType.ROW_STRIPED
                || tileB.getSpecialType() == SpecialType.COLUMN_STRIPED)) {
            // We make sure the origin special tiles not being detected
            tileA.setTileState(TileState.MATCH);
            tileB.setTileState(TileState.MATCH);
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }

        return false;
    }

    @Override
    protected void playTileEffect(Tile tileA, Tile tileB) {
        super.playTileEffect(tileA, tileB);
        mRowFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
        mColumnFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
    }
    private void handleSpecialCombine(Tile[][] tiles, Tile tileA, Tile tileB, int row, int col) {
        int targetRow = tileA.getRow();
        int targetCol = tileA.getColumn();

        // Pop row tile
        for (int j = 0; j < col; j++) {
            Tile t = tiles[targetRow][j];
            // We make sure not pop multiple times
            if (t.getTileState() == TileState.IDLE) {
                t.popTile();
            }
        }

        // Pop column tile
        for (int i = 0; i < row; i++) {
            Tile t = tiles[i][targetCol];
            // We make sure not pop multiple times
            if (t.getTileState() == TileState.IDLE) {
                t.popTile();
            }
        }

        playTileEffect(tileA, tileB);
    }

}
