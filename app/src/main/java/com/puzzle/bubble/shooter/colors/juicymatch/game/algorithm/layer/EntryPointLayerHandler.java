package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target.TargetHandlerManager;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.entrypoint.EntryPoint;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.entrypoint.EntryPointSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type.StarfishTile;
import com.puzzle.bubble.shooter.colors.juicymatch.level.TargetType;


public class EntryPointLayerHandler extends BaseLayerHandler {

    private final EntryPointSystem mEntryPointSystem;

    public EntryPointLayerHandler(EntryPointSystem entryPointSystem) {
        mEntryPointSystem = entryPointSystem;
    }
    @Override
    protected void onInitLayer(Tile tile) {
    }

    @Override
    protected void onUpdateLayer(Tile tile, TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col) {
        if (tile instanceof StarfishTile) {
            StarfishTile starfish = ((StarfishTile) tile);
            if (starfish.isStarfish()) {
                updateStarfish(targetHandlerManager, starfish);
            }
        }
    }

    @Override
    protected void onRemoveLayer(Tile tile) {
        // Remove starfish when layer removed
        if (tile instanceof StarfishTile) {
            StarfishTile starfish = ((StarfishTile) tile);
            if (!starfish.isStarfish()) {
                return;
            }
            starfish.popStarfishTile();
        }
    }
    private void updateStarfish(TargetHandlerManager targetHandlerManager, StarfishTile starfish) {
        // Check is starfish at entry point
        EntryPoint entryPoint = mEntryPointSystem.getChildAt(starfish.getRow(), starfish.getColumn());
        if (entryPoint != null && entryPoint.isRunning()) {
            // Remove starfish and update target
            starfish.popStarfishTile();
            targetHandlerManager.updateTarget(TargetType.STARFISH);
        }
    }

}
