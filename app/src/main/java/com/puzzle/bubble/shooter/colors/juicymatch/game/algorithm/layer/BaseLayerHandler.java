package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target.TargetHandlerManager;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;


public abstract class BaseLayerHandler implements LayerHandler {

    @Override
    public void initLayer(Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile t = tiles[i][j];
                onInitLayer(t);
            }
        }
    }

    @Override
    public void updateLayer(TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile t = tiles[i][j];
                onUpdateLayer(t, targetHandlerManager, tiles, row, col);
            }
        }
    }

    @Override
    public void removeLayer(Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile t = tiles[i][j];
                onRemoveLayer(t);
            }
        }
    }
    protected abstract void onInitLayer(Tile tile);

    protected abstract void onUpdateLayer(Tile tile, TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col);

    protected abstract void onRemoveLayer(Tile tile);

}
