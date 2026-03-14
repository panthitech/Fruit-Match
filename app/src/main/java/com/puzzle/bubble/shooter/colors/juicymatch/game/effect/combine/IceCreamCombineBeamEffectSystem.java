package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.combine;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash.FlashDirection;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;
import com.nativegame.natyengine.entity.modifier.DurationModifier;
import com.nativegame.natyengine.util.RandomUtils;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class IceCreamCombineBeamEffectSystem extends Entity {

    private static final long TIME_TO_LIVE = 2000;
    private static final long TIME_TO_SPAWN = 200;
    private static final int SPAWN_COUNT = 5;

    private final Pool<IceCreamCombineBeamEffect> mEffectPool;
    private final DurationModifier mDurationModifier;

    private float mTargetX;
    private float mTargetY;
    private long mTotalTime;

    public IceCreamCombineBeamEffectSystem(Engine engine, int count) {
        super(engine);
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<IceCreamCombineBeamEffect>() {
            @Override
            public IceCreamCombineBeamEffect createObject() {
                return new IceCreamCombineBeamEffect(IceCreamCombineBeamEffectSystem.this, engine,
                        Textures.FLASH_BEAM);
            }
        }, count);
        mDurationModifier = new DurationModifier(TIME_TO_LIVE);
        mDurationModifier.setAutoRemove(true);
    }
    @Override
    public void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        if (mTotalTime >= TIME_TO_SPAWN) {
            // Add beam in random direction
            int size = FlashDirection.values().length;
            for (int i = 0; i < SPAWN_COUNT; i++) {
                int index = RandomUtils.nextInt(size);
                mEffectPool.obtainObject().activate(mTargetX, mTargetY, FlashDirection.values()[index]);
            }
            mTotalTime = 0;
        }
        mDurationModifier.update(this, elapsedMillis);
    }
    public void activate(float x, float y) {
        mTargetX = x;
        mTargetY = y;
        mDurationModifier.init(this);
        addToGame();
        mTotalTime = 0;
    }

    public void returnToPool(IceCreamCombineBeamEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
