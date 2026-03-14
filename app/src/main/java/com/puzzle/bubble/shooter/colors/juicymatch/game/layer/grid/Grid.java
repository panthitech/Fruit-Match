package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.grid;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class Grid extends LayerSprite {

    private final GridType mGridType;

    public Grid(Engine engine, Texture texture, GridType gridType) {
        super(engine, texture);
        mGridType = gridType;
        setRotation(gridType.getAngle());
        setLayer(GameLayer.GRID_LAYER);
    }
    public GridType getGridType() {
        return mGridType;
    }

}
