package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class ExplosionPieceEffectSystem {

    private final Pool<ExplosionPieceEffect> mEffectPool;

    public ExplosionPieceEffectSystem(Engine engine, Texture texture, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<ExplosionPieceEffect>() {
            @Override
            public ExplosionPieceEffect createObject() {
                return new ExplosionPieceEffect(ExplosionPieceEffectSystem.this, engine, texture);
            }
        }, count);
    }
    public void activate(float x, float y, int count) {
        for (int i = 0; i < count; i++) {
            mEffectPool.obtainObject().activate(x, y);
        }
    }

    public void returnToPool(ExplosionPieceEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
