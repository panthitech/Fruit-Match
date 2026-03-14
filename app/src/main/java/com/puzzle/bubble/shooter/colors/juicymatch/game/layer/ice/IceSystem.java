package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.ice;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSpriteSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;


public class IceSystem extends LayerSpriteSystem<Ice> {

    private final Ice[][] mIces;

    public IceSystem(Engine engine) {
        super(engine);
        mIces = new Ice[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getIce().toCharArray());
    }
    @Override
    public Ice[][] getChild() {
        return mIces;
    }

    @Override
    public Ice getChildAt(int row, int col) {
        return mIces[row][col];
    }

    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                IceType type = IceInitializer.getType(c);
                int layer = IceInitializer.getLayer(c);
                Ice ice = new Ice(mEngine, type.getTexture(layer), type, layer);
                ice.setPosition(i, j);
                ice.addToGame();
                mIces[i][j] = ice;
            }
        }
    }

}
