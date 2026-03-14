package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece.StarfishPieceEffect;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.FruitType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class StarfishTile extends SolidTile {

    private final StarfishPieceEffect mStarfishPieceEffect;

    private boolean mIsStarfish = true;

    public StarfishTile(TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture, FruitType.NONE);
        mStarfishPieceEffect = new StarfishPieceEffect(engine, Textures.STARFISH);
    }
    public boolean isStarfish() {
        return mIsStarfish;
    }
    @Override
    public void popTile() {
        if (mIsStarfish) {
            return;
        }
        super.popTile();
    }

    @Override
    public void playTileEffect() {
        if (mIsStarfish) {
            playStarfishEffect();
            mIsStarfish = false;
            return;
        }
        super.playTileEffect();
    }
    public void popStarfishTile() {
        // Important to not reuse popTile() or matchTile()
        mTileState = TileState.MATCH;
    }

    private void playStarfishEffect() {
        mStarfishPieceEffect.activate(getCenterX(), getCenterY());
        Sounds.COLLECT_STARFISH.play();
    }

}
