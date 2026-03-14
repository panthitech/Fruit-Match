package com.puzzle.bubble.shooter.colors.juicymatch.game.booster;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameEvent;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameWorld;
import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.handler.ColumnStripedTileHandler;
import com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.handler.RowStripedTileHandler;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.booster.HammerEffect;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.type.EmptyTile;
import com.nativegame.natyengine.engine.Engine;


public class HammerController extends BoosterController {

    private final RowStripedTileHandler mRowStripedTileHandler;
    private final ColumnStripedTileHandler mColumnStripedTileHandler;
    private final HammerEffect mHammerEffect;

    public HammerController(Engine engine, TileSystem tileSystem) {
        super(engine, tileSystem);
        mRowStripedTileHandler = new RowStripedTileHandler(engine);
        mColumnStripedTileHandler = new ColumnStripedTileHandler(engine);
        mHammerEffect = new HammerEffect(engine, Textures.HAMMER);
    }
    @Override
    protected boolean isAddBooster(Tile touchDownTile, Tile touchUpTile) {
        return !(touchDownTile instanceof EmptyTile);
    }

    @Override
    protected void onAddBooster(Tile[][] tiles, Tile touchDownTile, Tile touchUpTile, int row, int col) {
        mHammerEffect.activate(GameWorld.WORLD_WIDTH / 2f, GameWorld.WORLD_HEIGHT / 2f,
                touchDownTile.getX(), touchDownTile.getY());
        Sounds.TILE_SLIDE.play();
    }

    @Override
    protected void onRemoveBooster(Tile[][] tiles, Tile touchDownTile, Tile touchUpTile, int row, int col) {
        mRowStripedTileHandler.handleSpecialTile(tiles, touchDownTile, row, col);
        mColumnStripedTileHandler.handleSpecialTile(tiles, touchDownTile, row, col);
        dispatchEvent(GameEvent.PLAYER_USE_BOOSTER);
    }

}
