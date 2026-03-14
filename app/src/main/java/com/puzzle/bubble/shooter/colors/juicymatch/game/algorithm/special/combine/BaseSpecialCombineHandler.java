package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.combine;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;


public abstract class BaseSpecialCombineHandler implements SpecialCombineHandler {

    protected final Engine mEngine;
    private final ParticleSystem mLightBgParticleSystem;

    protected BaseSpecialCombineHandler(Engine engine) {
        mEngine = engine;
        mLightBgParticleSystem = new SpriteParticleSystem(engine, Textures.LIGHT_BG, 1)
                .setDuration(750)
                .setAlpha(255, 0)
                .setScale(5, 5)
                .setLayer(GameLayer.EFFECT_BG_LAYER);
    }
    protected void playTileEffect(Tile tileA, Tile tileB) {
        // Add light bg
        mLightBgParticleSystem.oneShot(tileA.getCenterX(), tileA.getCenterY(), 1);
    }

}
