package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.sprite.AnimateSprite;
import com.nativegame.natyengine.texture.texture2d.Texture2DGroup;


public class ExplosionFlashEffect extends AnimateSprite {

    private static final long TIME_TO_ANIMATE = 400;
    private static final long TIME_TO_FADE = 500;

    private final ExplosionFlashEffectSystem mParent;
    private final FadeOutModifier mFadeOutModifier;

    public ExplosionFlashEffect(ExplosionFlashEffectSystem explosionFlashEffectSystem, Engine engine, Texture2DGroup textureGroup) {
        super(engine, textureGroup);
        mParent = explosionFlashEffectSystem;
        mFadeOutModifier = new FadeOutModifier(TIME_TO_FADE, TIME_TO_ANIMATE);
        mFadeOutModifier.setAutoRemove(true);
        setAnimation(40, false);
        setAnimationAutoStart(true);
        setLayer(GameLayer.EFFECT_LAYER);
    }
    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mFadeOutModifier.update(this, elapsedMillis);
    }
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mFadeOutModifier.init(this);
        addToGame();
    }

}
