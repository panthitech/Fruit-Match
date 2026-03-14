package com.puzzle.bubble.shooter.colors.juicymatch.game.layer;

import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;


public abstract class LayerSpriteSystem<T extends LayerSprite> extends Entity {

    protected final int mTotalRow;
    protected final int mTotalCol;

    protected LayerSpriteSystem(Engine engine) {
        super(engine);
        mTotalRow = Level.LEVEL_DATA.getRow();
        mTotalCol = Level.LEVEL_DATA.getColumn();
    }
    public int getTotalRow() {
        return mTotalRow;
    }

    public int getTotalColumn() {
        return mTotalCol;
    }

    public abstract T[][] getChild();

    public abstract T getChildAt(int row, int col);

}
