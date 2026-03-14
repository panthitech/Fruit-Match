package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.combine;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeInModifier;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;


public class IceCreamCombineRingEffect extends Sprite {

    private static final long TIME_TO_LIVE = 600;

    private final IceCreamCombineRingEffectSystem mParent;
    private final ScaleModifier mScaleModifier;
    private final FadeInModifier mFadeInModifier;

    public IceCreamCombineRingEffect(IceCreamCombineRingEffectSystem iceCreamCombineRingEffectSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = iceCreamCombineRingEffectSystem;
        mScaleModifier = new ScaleModifier(TIME_TO_LIVE);
        mFadeInModifier = new FadeInModifier(TIME_TO_LIVE);
        mFadeInModifier.setAutoRemove(true);
        setLayer(GameLayer.EFFECT_LAYER);
    }
    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mScaleModifier.update(this, elapsedMillis);
        mFadeInModifier.update(this, elapsedMillis);
    }
    public void activate(float x, float y, float scale) {
        setCenterX(x);
        setCenterY(y);
        mScaleModifier.setValue(scale, 0);
        mScaleModifier.init(this);
        mFadeInModifier.init(this);
        addToGame();
    }

}
