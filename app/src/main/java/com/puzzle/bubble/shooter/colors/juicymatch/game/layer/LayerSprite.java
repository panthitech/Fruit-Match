package com.puzzle.bubble.shooter.colors.juicymatch.game.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.game.GameWorld;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;


public abstract class LayerSprite extends Sprite {

    protected final int mMarginX;
    protected final int mMarginY;

    protected int mRow;
    protected int mCol;

    protected LayerSprite(Engine engine, Texture texture) {
        super(engine, texture);
        mMarginX = (GameWorld.WORLD_WIDTH - Level.LEVEL_DATA.getColumn() * getWidth()) / 2;
        mMarginY = (GameWorld.WORLD_HEIGHT - Level.LEVEL_DATA.getRow() * getHeight()) / 2;
    }
    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        mRow = row;
    }

    public int getColumn() {
        return mCol;
    }

    public void setColumn(int column) {
        mCol = column;
    }

    public void setPosition(int row, int col) {
        mRow = row;
        mCol = col;
        mX = col * getWidth() + mMarginX;
        mY = row * getHeight() + mMarginY;
    }

}
