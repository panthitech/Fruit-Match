package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class DummyTile extends ObstacleTile {

    private Tile mTargetTile;

    public DummyTile(TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture);
        setActive(false);
        setVisible(false);
    }
    public Tile getTargetTile() {
        return mTargetTile;
    }

    public void setTargetTile(Tile targetTile) {
        mTargetTile = targetTile;
    }
    @Override
    public boolean isSwappable() {
        if (mIsObstacle) {
            return false;
        }
        return super.isSwappable();
    }

    @Override
    public void popTile() {
        if (mIsObstacle) {
            // We shift the popTile call to the target tile
            mTargetTile.popTile();
            return;
        }
        super.popTile();
    }
    public void popDummyTile() {
        // Remove the dummy effect
        mTileState = TileState.MATCH;
        mIsObstacle = false;
        setActive(true);
        setVisible(true);
    }

}
