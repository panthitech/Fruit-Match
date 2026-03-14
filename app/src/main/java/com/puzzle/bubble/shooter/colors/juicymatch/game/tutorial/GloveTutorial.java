package com.puzzle.bubble.shooter.colors.juicymatch.game.tutorial;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameWorld;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.tutorial.TutorialFingerEffect;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.tutorial.TutorialHintEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;


public class GloveTutorial implements Tutorial {

    private final TutorialHintEffectSystem mHintEffect;
    private final TutorialFingerEffect mFingerEffect;

    public GloveTutorial(Engine engine) {
        mHintEffect = new TutorialHintEffectSystem(engine);
        mFingerEffect = new TutorialFingerEffect(engine, Textures.TUTORIAL_FINGER);
    }
    @Override
    public void show(GameActivity activity) {
        mHintEffect.activate(Level.LEVEL_DATA.getTutorialHint().toCharArray());
        int marginX = (GameWorld.WORLD_WIDTH - Level.LEVEL_DATA.getColumn() * 300) / 2;
        int marginY = (GameWorld.WORLD_HEIGHT - Level.LEVEL_DATA.getRow() * 300) / 2;
        mFingerEffect.activate(marginX + 1200, marginX + 1200, marginY + 100, marginY + 400);

        // Click the booster button
        GameButton btnGlove = (GameButton) activity.findViewById(R.id.btn_glove);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnGlove.performClick();
            }
        });
    }

}
