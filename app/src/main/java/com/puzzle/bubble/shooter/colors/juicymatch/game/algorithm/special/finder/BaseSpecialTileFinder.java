package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.finder;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;


public abstract class BaseSpecialTileFinder implements SpecialTileFinder {

    protected static final int MAX_FIND_NUM = 3;

    private final ParticleSystem mLightBgParticleSystem;

    protected BaseSpecialTileFinder(Engine engine) {
        mLightBgParticleSystem = new SpriteParticleSystem(engine, Textures.LIGHT_BG, MAX_FIND_NUM)
                .setDuration(750)
                .setAlpha(255, 0)
                .setScale(3, 3)
                .setLayer(GameLayer.EFFECT_LAYER);
    }
    protected abstract void setUpgradeTiles(Tile[][] tiles, int row, int col);

    protected void playUpgradeEffect(Tile tile) {
        // Add light bg
        mLightBgParticleSystem.oneShot(tile.getCenterX(), tile.getCenterY(), 1);
    }

}
