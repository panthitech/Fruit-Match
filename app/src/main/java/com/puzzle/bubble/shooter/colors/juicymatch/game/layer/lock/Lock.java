package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.lock;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece.ExplosionPieceEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class Lock extends LayerSprite {

    private static final int LOCK_PIECE = 4;

    private final LockType mLockType;

    private final ExplosionPieceEffectSystem mLockPieceEffect;

    public Lock(Engine engine, Texture texture, LockType lockType) {
        super(engine, texture);
        mLockType = lockType;
        mLockPieceEffect = new ExplosionPieceEffectSystem(engine, Textures.LOCK_PIECE, LOCK_PIECE);
        setLayer(GameLayer.LOCK_LAYER);
    }
    public LockType getLockType() {
        return mLockType;
    }
    public void playLockEffect() {
        mLockPieceEffect.activate(getCenterX(), getCenterY(), LOCK_PIECE);
        Sounds.LOCK_EXPLODE.play();
        // Remove the lock
        removeFromGame();
    }

}
