package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSpriteSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileResetter;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;


public class GeneratorSystem extends LayerSpriteSystem<Generator> {

    private final Generator[][] mGenerators;

    private final List<TileResetter> mResetters = new ArrayList<>();

    public GeneratorSystem(Engine engine) {
        super(engine);
        mGenerators = new Generator[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getGenerator().toCharArray());
    }
    public List<TileResetter> getResetters() {
        return mResetters;
    }
    @Override
    public Generator[][] getChild() {
        return mGenerators;
    }

    @Override
    public Generator getChildAt(int row, int col) {
        return mGenerators[row][col];
    }
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                Generator generator = GeneratorInitializer.getGenerator(mEngine, c);
                generator.setPosition(i, j);
                generator.addToGame();
                mGenerators[i][j] = generator;
                mResetters.add(generator.getResetter());
            }
        }
    }

}
