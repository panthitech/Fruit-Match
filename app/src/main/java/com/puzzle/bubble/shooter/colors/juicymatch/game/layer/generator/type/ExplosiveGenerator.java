package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.type;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.Generator;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.SpecialType;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileResetter;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;


public class ExplosiveGenerator extends Generator {

    private static int mCount;

    private final ExplosionSpecialTileGeneratorResetter mResetter = new ExplosionSpecialTileGeneratorResetter();

    public ExplosiveGenerator(Engine engine, Texture texture) {
        super(engine, texture);
        mCount = 9;   // Generate at first match
    }
    @Override
    public TileResetter getResetter() {
        return mResetter;
    }
    private class ExplosionSpecialTileGeneratorResetter implements TileResetter {

        private static final int MAX_COUNT = 12;

        @Override
        public void resetTile(Tile tile) {
            // Check is generator and tile at the same column
            if (tile.getColumn() == mCol) {
                // Update and check the accumulated count
                mCount++;
                if (mCount >= MAX_COUNT) {
                    tile.setSpecialType(SpecialType.EXPLOSIVE);
                    playGeneratorEffect();
                    mCount = 0;
                }
            }
        }

    }

}
