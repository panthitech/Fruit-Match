package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class ExplosionBeamEffectSystem {

    private final Pool<ExplosionBeamEffect> mEffectPool;

    public ExplosionBeamEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<ExplosionBeamEffect>() {
            @Override
            public ExplosionBeamEffect createObject() {
                return new ExplosionBeamEffect(ExplosionBeamEffectSystem.this, engine, Textures.FLASH_BEAM);
            }
        }, count);
    }
    public void activate(float x, float y) {
        int size = FlashDirection.values().length;
        for (int i = 0; i < size; i++) {
            mEffectPool.obtainObject().activate(x, y, FlashDirection.values()[i]);
        }
    }

    public void returnToPool(ExplosionBeamEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
