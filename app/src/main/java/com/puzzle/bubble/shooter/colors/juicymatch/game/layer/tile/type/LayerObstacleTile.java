package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public abstract class LayerObstacleTile extends ObstacleTile {

    protected int mObstacleLayer;

    protected LayerObstacleTile(TileSystem tileSystem, Engine engine, Texture texture, int obstacleLayer) {
        super(tileSystem, engine, texture);
        mObstacleLayer = obstacleLayer;
    }
    public int getObstacleLayer() {
        return mObstacleLayer;
    }
    @Override
    public void playTileEffect() {
        if (mIsObstacle) {
            // Reduce one layer and update state
            mObstacleLayer--;
            onUpdateLayer(mObstacleLayer);
            if (mObstacleLayer == 0) {
                // Clear the obstacle
                mIsObstacle = false;
            } else {
                // We prevent the obstacle from reset
                mTileState = TileState.IDLE;
            }
            return;
        }
        super.playTileEffect();
    }
    protected abstract void onUpdateLayer(int obstacleLayer);

}
