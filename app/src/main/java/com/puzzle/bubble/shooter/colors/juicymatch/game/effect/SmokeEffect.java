package com.puzzle.bubble.shooter.colors.juicymatch.game.effect;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.sprite.AnimateSprite;
import com.nativegame.natyengine.texture.texture2d.Texture2DGroup;


public class SmokeEffect extends AnimateSprite {

    private static final long TIME_TO_LIVE = 400;

    private final FadeOutModifier mFadeOutModifier;

    public SmokeEffect(Engine engine, Texture2DGroup textureGroup) {
        super(engine, textureGroup);
        mFadeOutModifier = new FadeOutModifier(TIME_TO_LIVE);
        setAnimation(50, false);
        setAnimationAutoStart(true);
        setAnimationAutoRemove(true);
        setLayer(GameLayer.EFFECT_LAYER);
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
