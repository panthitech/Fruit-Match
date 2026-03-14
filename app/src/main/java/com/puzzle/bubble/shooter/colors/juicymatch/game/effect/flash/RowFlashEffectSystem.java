package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class RowFlashEffectSystem {

    private final Pool<RowFlashEffect> mEffectPool;

    public RowFlashEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<RowFlashEffect>() {
            @Override
            public RowFlashEffect createObject() {
                return new RowFlashEffect(RowFlashEffectSystem.this, engine, Textures.FLASH_ROW_ANIMATION);
            }
        }, count * 2);
    }
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y, FlashDirection.LEFT);
        mEffectPool.obtainObject().activate(x, y, FlashDirection.RIGHT);
        Sounds.STRIPED_EXPLODE.play();
    }

    public void returnToPool(RowFlashEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
