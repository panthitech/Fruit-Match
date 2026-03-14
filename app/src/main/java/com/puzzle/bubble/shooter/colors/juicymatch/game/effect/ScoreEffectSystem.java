package com.puzzle.bubble.shooter.colors.juicymatch.game.effect;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;


public class ScoreEffectSystem {

    private final Pool<ScoreEffect> mEffectPool;

    public ScoreEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<ScoreEffect>() {
            @Override
            public ScoreEffect createObject() {
                return new ScoreEffect(ScoreEffectSystem.this, engine, Textures.SCORE_PINK);
            }
        }, count);
    }
    public void activate(float x, float y, FruitType fruitType) {
        mEffectPool.obtainObject().activate(x, y, fruitType);
    }

    public void returnToPool(ScoreEffect effect) {
        mEffectPool.returnObject(effect);
    }

}
