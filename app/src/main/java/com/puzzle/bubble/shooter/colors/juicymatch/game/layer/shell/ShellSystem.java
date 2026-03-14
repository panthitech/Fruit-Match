package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.shell;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSpriteSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;


public class ShellSystem extends LayerSpriteSystem<Shell> {

    private final Shell[][] mShells;

    public ShellSystem(Engine engine) {
        super(engine);
        mShells = new Shell[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getShell().toCharArray());
    }
    @Override
    public Shell[][] getChild() {
        return mShells;
    }

    @Override
    public Shell getChildAt(int row, int col) {
        return mShells[row][col];
    }
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                ShellType type = ShellInitializer.getType(c);
                Shell shell = new Shell(mEngine, Textures.EMPTY, type);
                shell.setPosition(i, j);
                shell.addToGame();
                mShells[i][j] = shell;
            }
        }
    }

}
