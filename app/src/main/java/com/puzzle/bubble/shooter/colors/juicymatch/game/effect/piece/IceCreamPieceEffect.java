package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.ScaleOutModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;


public class IceCreamPieceEffect extends Sprite {

    private static final long TIME_TO_LIVE = 500;

    private final IceCreamPieceEffectSystem mParent;
    private final ScaleOutModifier mScaleOutModifier;

    public IceCreamPieceEffect(IceCreamPieceEffectSystem iceCreamPieceEffectSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = iceCreamPieceEffectSystem;
        mScaleOutModifier = new ScaleOutModifier(TIME_TO_LIVE);
        mScaleOutModifier.setAutoRemove(true);
        setLayer(GameLayer.EFFECT_LAYER);
    }
    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mScaleOutModifier.update(this, elapsedMillis);
    }
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mScaleOutModifier.init(this);
        addToGame();
    }

}
