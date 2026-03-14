package com.puzzle.bubble.shooter.colors.juicymatch.game.swap;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;


public class SwapModifier extends Entity {

    private SwapListener mListener;
    private Tile mTileA;
    private Tile mTileB;

    public SwapModifier(Engine engine) {
        super(engine);
    }
    public SwapListener getListener() {
        return mListener;
    }

    public void setListener(SwapListener listener) {
        mListener = listener;
    }
    @Override
    public void onUpdate(long elapsedMillis) {
        mTileA.swapTile(elapsedMillis);
        mTileB.swapTile(elapsedMillis);
        // Remove after swap finished
        if (!mTileA.isMoving() && !mTileB.isMoving()) {
            if (mListener != null) {
                mListener.onSwap(mTileA, mTileB);
            }
            removeFromGame();
        }
    }
    public void activate(Tile tileA, Tile tileB) {
        mTileA = tileA;
        mTileB = tileB;
        Sounds.TILE_SWAP.play();
        addToGame();
    }
    public interface SwapListener {

        void onSwap(Tile tileA, Tile tileB);

    }

}
