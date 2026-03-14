package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.sand;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece.ExplosionPieceEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class Sand extends LayerSprite {

    private static final int SAND_PIECES = 30;

    private final SandType mSandType;
    private final ExplosionPieceEffectSystem mYellowSandPieceEffect;
    private final ExplosionPieceEffectSystem mBrownSandPieceEffect;

    private int mSandLayer;

    public Sand(Engine engine, Texture texture, SandType sandType, int sandLayer) {
        super(engine, texture);
        mSandType = sandType;
        mSandLayer = sandLayer;
        mYellowSandPieceEffect = new ExplosionPieceEffectSystem(engine, Textures.YELLOW_SAND_PIECE, SAND_PIECES);
        mBrownSandPieceEffect = new ExplosionPieceEffectSystem(engine, Textures.BROWN_SAND_PIECE, SAND_PIECES);
        setRotation(sandType.getAngle());
        setLayer(GameLayer.SAND_LAYER);
    }
    public SandType getSandType() {
        return mSandType;
    }

    public int getSandLayer() {
        return mSandLayer;
    }
    public void playSandEffect() {
        // Update sand layer
        mSandLayer--;

        if (mSandLayer == 0) {
            // Remove the sand
            removeFromGame();
            mYellowSandPieceEffect.activate(getCenterX(), getCenterY(), SAND_PIECES);
        } else {
            // Update sand texture
            setTexture(mSandType.getTexture(mSandLayer));
            mBrownSandPieceEffect.activate(getCenterX(), getCenterY(), SAND_PIECES);
        }
        Sounds.SAND_EXPLODE.play();
    }

}
