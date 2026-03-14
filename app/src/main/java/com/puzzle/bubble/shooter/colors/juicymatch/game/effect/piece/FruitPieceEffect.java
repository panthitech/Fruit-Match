package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.modifier.PositionModifier;
import com.nativegame.natyengine.entity.modifier.RotationModifier;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;


public class FruitPieceEffect extends Sprite {

    private static final long TIME_TO_LIVE = 500;

    private final FruitPieceEffectSystem mParent;
    private final ScaleModifier mScaleModifier;
    private final RotationModifier mRotationModifier;
    private final FadeOutModifier mFadeOutModifier;
    private final PositionModifier mPositionModifier;

    public FruitPieceEffect(FruitPieceEffectSystem fruitPieceEffectSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = fruitPieceEffectSystem;
        mScaleModifier = new ScaleModifier(1, 0.5f, TIME_TO_LIVE);
        mRotationModifier = new RotationModifier(0, -45, TIME_TO_LIVE);
        mFadeOutModifier = new FadeOutModifier(TIME_TO_LIVE);
        mPositionModifier = new PositionModifier(TIME_TO_LIVE);
        mPositionModifier.setAutoRemove(true);
        setLayer(GameLayer.EFFECT_LAYER + 1);   // Fruit pieces need to be above flash effect
    }
    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mScaleModifier.update(this, elapsedMillis);
        mRotationModifier.update(this, elapsedMillis);
        mFadeOutModifier.update(this, elapsedMillis);
        mPositionModifier.update(this, elapsedMillis);
    }
    public void activate(float x, float y, FruitType fruitType, SpecialType specialType, FruitPiece direction) {
        setCenterX(x);
        setCenterY(y);
        if (specialType != SpecialType.NONE) {
            setTexture(specialType.getPiecesTexture(fruitType)[direction.getIndex()]);
        } else {
            setTexture(fruitType.getPiecesTexture()[direction.getIndex()]);
        }
        mScaleModifier.init(this);
        mRotationModifier.init(this);
        mFadeOutModifier.init(this);
        mPositionModifier.setValue(mX, mX + 80 * direction.getDirection(), mY, mY + 80);
        mPositionModifier.init(this);
        addToGame();
    }

}
