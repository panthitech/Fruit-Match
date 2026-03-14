package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.Match3Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileState;
import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.TileType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameLayer;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public abstract class Tile extends LayerSprite implements Match3Tile {

    protected FruitType mFruitType;
    protected SpecialType mSpecialType;
    protected TileState mTileState;
    private boolean mIsSelect = false;

    protected Tile(Engine engine, Texture texture, FruitType fruitType) {
        this(engine, texture, fruitType, SpecialType.NONE, TileState.IDLE);
    }

    protected Tile(Engine engine, Texture texture, FruitType fruitType, TileState tileState) {
        this(engine, texture, fruitType, SpecialType.NONE, tileState);
    }

    protected Tile(Engine engine, Texture texture, FruitType fruitType, SpecialType specialType) {
        this(engine, texture, fruitType, specialType, TileState.IDLE);
    }

    protected Tile(Engine engine, Texture texture, FruitType fruitType, SpecialType specialType, TileState tileState) {
        super(engine, texture);
        mFruitType = fruitType;
        mSpecialType = specialType;
        mTileState = tileState;
        setLayer(GameLayer.TILE_LAYER);
    }
    public SpecialType getSpecialType() {
        return mSpecialType;
    }

    public void setSpecialType(SpecialType specialType) {
        mSpecialType = specialType;
        if (specialType != SpecialType.UPGRADE) {
            // Put the tile back to idle state
            mTileState = TileState.IDLE;
            // No texture for UPGRADE
            setTexture(specialType.getTexture(mFruitType));
        }
    }

    public boolean isSelect() {
        return mIsSelect;
    }

    public void setSelect(boolean select) {
        mIsSelect = select;
    }
    @Override
    public TileState getTileState() {
        return mTileState;
    }

    @Override
    public void setTileState(TileState tileState) {
        mTileState = tileState;
    }

    @Override
    public TileType getTileType() {
        return mFruitType;
    }

    @Override
    public void setTileType(TileType tileType) {
        FruitType fruitType = ((FruitType) tileType);
        mFruitType = fruitType;
        if (fruitType != FruitType.NONE) {
            // No texture for NONE
            setTexture(fruitType.getTexture());
        }
    }
    public abstract void addResetter(TileResetter resetter);

    public abstract void removeResetter(TileResetter resetter);

    public abstract void selectTile();

    public abstract void unSelectTile();

}
