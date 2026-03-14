package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.honey;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSpriteSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;


public class HoneySystem extends LayerSpriteSystem<Honey> {

    private final Honey[][] mHoneys;

    public HoneySystem(Engine engine) {
        super(engine);
        mHoneys = new Honey[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getHoney().toCharArray());
    }
    @Override
    public Honey[][] getChild() {
        return mHoneys;
    }

    @Override
    public Honey getChildAt(int row, int col) {
        return mHoneys[row][col];
    }
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                HoneyType type = HoneyInitialize.getType(Character.toLowerCase(c));
                Honey honey = new Honey(mEngine, type.getTexture(), type);
                honey.setPosition(i, j);
                if (Character.isUpperCase(c)) {
                    honey.addToGame();
                }
                mHoneys[i][j] = honey;
            }
        }
    }

}

