package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.lock;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSpriteSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;


public class LockSystem extends LayerSpriteSystem<Lock> {

    private final Lock[][] mLocke;

    public LockSystem(Engine engine) {
        super(engine);
        mLocke = new Lock[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getLock().toCharArray());
    }
    @Override
    public Lock[][] getChild() {
        return mLocke;
    }

    @Override
    public Lock getChildAt(int row, int col) {
        return mLocke[row][col];
    }
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                LockType type = LockInitializer.getType(c);
                Lock lock = new Lock(mEngine, type.getTexture(), type);
                lock.setPosition(i, j);
                lock.addToGame();
                mLocke[i][j] = lock;
            }
        }
    }

}
