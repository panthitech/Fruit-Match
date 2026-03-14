package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.tutorial;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.primitive.Plane;
import com.nativegame.natyengine.input.touch.TouchEventListener;


public class TutorialShadowEffect extends Plane implements TouchEventListener {

    public TutorialShadowEffect(Engine engine, int color) {
        super(engine, color);
    }
    @Override
    public void onTouchEvent(int type, float touchX, float touchY) {
        // Remove when player touch screen
        removeFromGame();
    }

}
