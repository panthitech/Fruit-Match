package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.tutorial;

import android.graphics.Color;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameWorld;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.primitive.Rectangle;
import com.nativegame.natyengine.input.touch.TouchEventListener;


public class TutorialHintEffect extends Rectangle implements TouchEventListener {

    protected final int mMarginX;
    protected final int mMarginY;

    public TutorialHintEffect(Engine engine, int width, int height) {
        super(engine, width, height);
        mMarginX = (GameWorld.WORLD_WIDTH - Level.LEVEL_DATA.getColumn() * width) / 2;
        mMarginY = (GameWorld.WORLD_HEIGHT - Level.LEVEL_DATA.getRow() * height) / 2;
        mPaint.setColor(Color.BLACK);
        setAlpha(200);
        setLayer(GameLayer.EFFECT_LAYER);
    }
    @Override
    public void onTouchEvent(int type, float touchX, float touchY) {
        // Remove when player touch screen
        removeFromGame();
    }
    public void activate(float x, float y) {
        mX = x + mMarginX;
        mY = y + mMarginY;
        addToGame();
    }

}
