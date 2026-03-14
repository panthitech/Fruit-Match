package com.puzzle.bubble.shooter.colors.juicymatch.game.booster;

import com.puzzle.bubble.shooter.colors.juicymatch.algorithm.Match3Algorithm;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameEvent;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameWorld;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.booster.GloveEffect;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.swap.SwapModifier;
import com.nativegame.natyengine.engine.Engine;


public class GloveController extends BoosterController implements SwapModifier.SwapListener {

    private final SwapModifier mSwapModifier;
    private final GloveEffect mGloveEffect;

    public GloveController(Engine engine, TileSystem tileSystem) {
        super(engine, tileSystem);
        mSwapModifier = new SwapModifier(engine);
        mSwapModifier.setListener(this);
        mGloveEffect = new GloveEffect(engine, Textures.GLOVE);
    }
    @Override
    protected boolean isAddBooster(Tile touchDownTile, Tile touchUpTile) {
        return touchUpTile != null && touchDownTile.isSwappable() && touchUpTile.isSwappable();
    }

    @Override
    protected void onAddBooster(Tile[][] tiles, Tile touchDownTile, Tile touchUpTile, int row, int col) {
        mGloveEffect.activate(GameWorld.WORLD_WIDTH / 2f, GameWorld.WORLD_HEIGHT / 2f,
                touchDownTile.getX(), touchDownTile.getY(), touchUpTile.getX(), touchUpTile.getY());
        Sounds.TILE_SLIDE.play();
    }

    @Override
    protected void onRemoveBooster(Tile[][] tiles, Tile touchDownTile, Tile touchUpTile, int row, int col) {
        Match3Algorithm.swapTile(tiles, touchDownTile, touchUpTile);
        mSwapModifier.activate(touchDownTile, touchUpTile);
    }

    @Override
    public void onSwap(Tile tileA, Tile tileB) {
        dispatchEvent(GameEvent.PLAYER_USE_BOOSTER);
    }

}
