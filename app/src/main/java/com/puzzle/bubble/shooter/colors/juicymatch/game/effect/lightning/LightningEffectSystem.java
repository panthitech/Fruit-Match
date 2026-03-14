package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.lightning;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class LightningEffectSystem {

    private final Pool<LightningEffect> mEffectPool;

    public LightningEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<LightningEffect>() {
            @Override
            public LightningEffect createObject() {
                return new LightningEffect(LightningEffectSystem.this, engine, Textures.LIGHTNING_ANIMATION);
            }
        }, count);
    }
    public void activate(float x, float y, int height, int rotation) {
        mEffectPool.obtainObject().activate(x, y, height, rotation);
    }

    public void returnToPool(LightningEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
