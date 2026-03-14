package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public abstract class ObstacleTile extends SolidTile {

    protected boolean mIsObstacle = true;

    protected ObstacleTile(TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture, FruitType.NONE);
    }
    public boolean isObstacle() {
        return mIsObstacle;
    }
    @Override
    public void popTile() {
        if (mIsObstacle) {
            mTileState = TileState.MATCH;
            return;
        }
        super.popTile();
    }

}
