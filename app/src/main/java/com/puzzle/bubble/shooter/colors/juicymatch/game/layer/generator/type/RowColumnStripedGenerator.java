package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.type;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.Generator;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileResetter;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.RandomUtils;


public class RowColumnStripedGenerator extends Generator {

    private static int mCount;

    private final RowColumnStripedGeneratorResetter mResetter = new RowColumnStripedGeneratorResetter();

    public RowColumnStripedGenerator(Engine engine, Texture texture) {
        super(engine, texture);
        mCount = 9;   // Generate at first match
    }
    @Override
    public TileResetter getResetter() {
        return mResetter;
    }
    private class RowColumnStripedGeneratorResetter implements TileResetter {

        private static final int MAX_COUNT = 12;

        @Override
        public void resetTile(Tile tile) {
            // Check is generator and tile at the same column
            if (tile.getColumn() == mCol) {
                // Update and check the accumulated count
                mCount++;
                if (mCount >= MAX_COUNT) {
                    if (RandomUtils.nextBoolean()) {
                        tile.setSpecialType(SpecialType.ROW_STRIPED);
                    } else {
                        tile.setSpecialType(SpecialType.COLUMN_STRIPED);
                    }
                    playGeneratorEffect();
                    mCount = 0;
                }
            }
        }

    }

}
