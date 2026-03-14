package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class TransformFlashEffectSystem {

    private final Pool<TransformFlashEffect> mEffectPool;

    public TransformFlashEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<TransformFlashEffect>() {
            @Override
            public TransformFlashEffect createObject() {
                return new TransformFlashEffect(TransformFlashEffectSystem.this, engine,
                        Textures.FLASH_TRANSFORM_ANIMATION);
            }
        }, count);
    }
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y);
    }

    public void returnToPool(TransformFlashEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
