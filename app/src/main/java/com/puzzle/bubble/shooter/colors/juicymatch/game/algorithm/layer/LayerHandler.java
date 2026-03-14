package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target.TargetHandlerManager;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;


public interface LayerHandler {

    void initLayer(Tile[][] tiles, int row, int col);

    void updateLayer(TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col);

    void removeLayer(Tile[][] tiles, int row, int col);

}
