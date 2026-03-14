package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target.TargetHandlerManager;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.ice.Ice;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.ice.IceSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.level.TargetType;


public class IceLayerHandler extends BaseLayerHandler {

    private final IceSystem mIceSystem;

    public IceLayerHandler(IceSystem iceSystem) {
        mIceSystem = iceSystem;
    }
    @Override
    protected void onInitLayer(Tile tile) {
    }

    @Override
    protected void onUpdateLayer(Tile tile, TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col) {
        if (tile.getTileState() != TileState.MATCH) {
            return;
        }
        updateIce(targetHandlerManager, tile);
    }

    @Override
    protected void onRemoveLayer(Tile tile) {
    }
    private void updateIce(TargetHandlerManager targetHandlerManager, Tile tile) {
        // Check is tile has ice
        Ice ice = mIceSystem.getChildAt(tile.getRow(), tile.getColumn());
        if (ice != null && ice.isRunning()) {
            // Remove ice if tile is fruit or special tile
            if (tile.getTileType() != FruitType.NONE || tile.getSpecialType() != SpecialType.NONE) {
                ice.playIceEffect();
                // Update target if all the ice layers being removed
                if (ice.getIceLayer() == 0) {
                    targetHandlerManager.updateTarget(TargetType.ICE);
                }
            }
        }
    }

}
