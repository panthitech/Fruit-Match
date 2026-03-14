package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile;

import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.LayerSpriteSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.engine.Engine;


public class TileSystem extends LayerSpriteSystem<Tile> {

    private final Tile[][] mTiles;

    public TileSystem(Engine engine) {
        super(engine);
        mTiles = new Tile[mTotalRow][mTotalCol];
        initTile(Level.LEVEL_DATA.getTile().toCharArray());
    }
    @Override
    public Tile[][] getChild() {
        return mTiles;
    }

    @Override
    public Tile getChildAt(int row, int col) {
        return mTiles[row][col];
    }
    private void initTile(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                Tile tile = TileInitializer.createTile(this, mEngine, i, j, c);
                tile.setPosition(i, j);
                if (c != 'e') {
                    // We do not add empty tile
                    tile.addToGame();
                }
                mTiles[i][j] = tile;
            }
        }
    }

}
