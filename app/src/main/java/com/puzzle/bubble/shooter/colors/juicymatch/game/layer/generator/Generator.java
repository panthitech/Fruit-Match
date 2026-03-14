package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileResetter;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.RotationModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;


public abstract class Generator extends LayerSprite {

    private final GeneratorPivot mPivot;

    public Generator(Engine engine, Texture texture) {
        super(engine, texture);
        mPivot = new GeneratorPivot(engine, Textures.GENERATOR_PIVOT);
        setLayer(GameLayer.GENERATOR_LAYER);
    }
    @Override
    public void onStart() {
        mY -= 225;
        mPivot.setX(mX);
        mPivot.setEndY(getEndY());
        mPivot.addToGame();
    }
    public abstract TileResetter getResetter();

    public void playGeneratorEffect() {
        mPivot.activate();
    }
    private static class GeneratorPivot extends Sprite {

        private static final long TIME_TO_ROTATE = 500;
        private static final long TIME_TO_PAUSE = 200;   // We pause for a while before the tile appear

        private final RotationModifier mRotationModifier;

        public GeneratorPivot(Engine engine, Texture texture) {
            super(engine, texture);
            mRotationModifier = new RotationModifier(0, 180, TIME_TO_ROTATE, TIME_TO_PAUSE,
                    OvershootTweener.getInstance());
            setLayer(GameLayer.GENERATOR_LAYER);
        }
        @Override
        public void onUpdate(long elapsedMillis) {
            mRotationModifier.update(this, elapsedMillis);
        }
        public void activate() {
            mRotationModifier.init(this);
        }

    }

}
