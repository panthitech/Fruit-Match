package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.shell;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.modifier.PositionYModifier;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;


public class Shell extends LayerSprite {

    private final ShellType mShellType;
    private final ShellEffect mShellEffect;

    public Shell(Engine engine, Texture texture, ShellType sandType) {
        super(engine, texture);
        mShellType = sandType;
        mShellEffect = new ShellEffect(engine, sandType.getTexture());
        setLayer(GameLayer.SAND_LAYER - 1);
    }
    public ShellType getShellType() {
        return mShellType;
    }
    @Override
    public void onStart() {
        mShellEffect.activate(mX, mY);
    }
    public void playShellEffect() {
        mShellEffect.showRemoveEffect();
        removeFromGame();
        Sounds.COLLECT_SHELL.play();
    }
    private static class ShellEffect extends Sprite {

        private final ScaleModifier mBounceOutModifier;
        private final ScaleModifier mBounceInModifier;
        private final FadeOutModifier mFadeOutModifier;
        private final PositionYModifier mPositionModifier;

        public ShellEffect(Engine engine, Texture texture) {
            super(engine, texture);
            mBounceOutModifier = new ScaleModifier(1, 1.3f, 250);
            mBounceInModifier = new ScaleModifier(1.3f, 1, 250, 250,
                    OvershootTweener.getInstance());
            mBounceInModifier.setModifyBefore(false);
            mFadeOutModifier = new FadeOutModifier(500, 500);
            mPositionModifier = new PositionYModifier(500, 500);
            mPositionModifier.setAutoRemove(true);
            setLayer(GameLayer.SAND_LAYER - 1);
        }
        @Override
        public void onUpdate(long elapsedMillis) {
            mBounceOutModifier.update(this, elapsedMillis);
            mBounceInModifier.update(this, elapsedMillis);
            mFadeOutModifier.update(this, elapsedMillis);
            mPositionModifier.update(this, elapsedMillis);
        }
        public void activate(float x, float y) {
            mX = x;
            mY = y;
            addToGame();
        }

        public void showRemoveEffect() {
            mBounceOutModifier.init(this);
            mBounceInModifier.init(this);
            mFadeOutModifier.init(this);
            mPositionModifier.setValue(mY, mY - 800);
            mPositionModifier.init(this);
            setLayer(GameLayer.TEXT_LAYER + 1);
        }

    }

}
