package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.honey;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece.HoneyPiece;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece.HoneyPieceEffect;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.ScaleInModifier;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;

import java.util.ArrayList;
import java.util.List;


public class Honey extends LayerSprite {

    private static final int HONEY_PIECE = 5;
    private static final long TIME_TO_SCALE_IN = 300;

    private final HoneyType mHoneyType;
    private final ScaleInModifier mScaleInModifier;

    private final List<HoneyPieceEffect> mHoneyPieceEffects = new ArrayList<>(HONEY_PIECE);

    public Honey(Engine engine, Texture texture, HoneyType honeyType) {
        super(engine, texture);
        mHoneyType = honeyType;
        mScaleInModifier = new ScaleInModifier(TIME_TO_SCALE_IN, OvershootTweener.getInstance());
        // Init honey pieces
        for (int i = 0; i < HONEY_PIECE; i++) {
            mHoneyPieceEffects.add(new HoneyPieceEffect(engine, Textures.HONEY_PIECE, HoneyPiece.values()[i]));
        }
        setRotation(honeyType.getAngle());
        setLayer(GameLayer.HONEY_LAYER);
    }
    public HoneyType getHoneyType() {
        return mHoneyType;
    }
    @Override
    public void onUpdate(long elapsedMillis) {
        mScaleInModifier.update(this, elapsedMillis);
    }
    public void playHoneyEffect() {
        // Play explosion effect
        for (int i = 0; i < HONEY_PIECE; i++) {
            mHoneyPieceEffects.get(i).activate(getCenterX(), getCenterY());
        }
        mHoneyPieceEffects.clear();
        Sounds.HONEY_EXPLODE.play();

        // Play show effect and add to game
        mScaleInModifier.init(this);
        addToGame();
    }

}
