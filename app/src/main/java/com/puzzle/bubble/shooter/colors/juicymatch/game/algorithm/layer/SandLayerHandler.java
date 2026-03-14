package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.target.TargetHandlerManager;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.sand.Sand;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.sand.SandSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.shell.Shell;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.shell.ShellSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.level.TargetType;


public class SandLayerHandler extends BaseLayerHandler {

    private final SandSystem mSandSystem;
    private final ShellSystem mShellSystem;

    public SandLayerHandler(SandSystem sandSystem, ShellSystem shellSystem) {
        mSandSystem = sandSystem;
        mShellSystem = shellSystem;
    }
    @Override
    public void updateLayer(TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col) {
        super.updateLayer(targetHandlerManager, tiles, row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile t = tiles[i][j];
                updateShell(targetHandlerManager, t);
            }
        }
    }

    @Override
    protected void onInitLayer(Tile tile) {
    }

    @Override
    protected void onUpdateLayer(Tile tile, TargetHandlerManager targetHandlerManager, Tile[][] tiles, int row, int col) {
        if (tile.getTileState() != TileState.MATCH) {
            return;
        }
        updateSand(tile);
    }

    @Override
    protected void onRemoveLayer(Tile tile) {
    }
    private void updateSand(Tile tile) {
        // Check is tile has sand
        Sand sand = mSandSystem.getChildAt(tile.getRow(), tile.getColumn());
        if (sand != null && sand.isRunning()) {
            // Remove sand if tile is fruit or special tile
            if (tile.getTileType() != FruitType.NONE || tile.getSpecialType() != SpecialType.NONE) {
                sand.playSandEffect();
            }
        }
    }

    private void updateShell(TargetHandlerManager targetHandlerManager, Tile tile) {
        int row = tile.getRow();
        int col = tile.getColumn();
        // Check is tile has shell
        Shell shell = mShellSystem.getChildAt(row, col);
        if (shell != null && shell.isRunning()) {
            // Check is all the sand cleared
            int width = shell.getShellType().getWidth();
            int height = shell.getShellType().getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Sand sand = mSandSystem.getChildAt(row + i, col + j);
                    if (sand != null && sand.isRunning()) {
                        return;
                    }
                }
            }
            // Update target and remove shell
            shell.playShellEffect();
            targetHandlerManager.updateTarget(TargetType.SHELL);
        }
    }

}
