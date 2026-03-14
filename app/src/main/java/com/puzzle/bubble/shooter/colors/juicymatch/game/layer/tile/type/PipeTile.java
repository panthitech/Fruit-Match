package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class PipeTile extends EmptyTile {

    public PipeTile(Engine engine, Texture texture) {
        super(engine, texture);
        // We let the pipe on top of tile, so they will pass underneath
        mLayer = GameLayer.TILE_LAYER + 1;
    }
    @Override
    public boolean isNegligible() {
        // We neglect pipe when swap to top
        return true;
    }

}
