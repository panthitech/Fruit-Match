package com.puzzle.bubble.shooter.colors.juicymatch.game.algorithm.special.combine;

import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.lightning.LightningEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.lightning.LightningGlitterEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece.IceCreamPieceEffectSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.Tile;
import com.nativegame.natyengine.engine.Engine;


public abstract class IceCreamCombineHandler extends BaseSpecialCombineHandler {

    private static final int MAX_LIGHTNING_NUM = 20;

    private final LightningEffectSystem mLightningEffectSystem;
    private final LightningGlitterEffectSystem mLightningGlitterEffectSystem;
    private final IceCreamPieceEffectSystem mIceCreamPieceEffectSystem;

    protected IceCreamCombineHandler(Engine engine) {
        super(engine);
        mLightningEffectSystem = new LightningEffectSystem(engine, MAX_LIGHTNING_NUM);
        mLightningGlitterEffectSystem = new LightningGlitterEffectSystem(engine, MAX_LIGHTNING_NUM);
        mIceCreamPieceEffectSystem = new IceCreamPieceEffectSystem(engine, 1);
    }
    @Override
    protected void playTileEffect(Tile colorTile, Tile fruitTile) {
        super.playTileEffect(colorTile, fruitTile);
        mIceCreamPieceEffectSystem.activate(colorTile.getCenterX(), colorTile.getCenterY());
    }
    protected void playLightningEffect(Tile colorTile, Tile targetTile) {
        // Calculate distance and angle between two tiles
        float distanceX = colorTile.getX() - targetTile.getX();
        float distanceY = colorTile.getY() - targetTile.getY();
        int distance = (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        int angle = getAngle(distanceX, distanceY);
        // Add one lightning and glitter
        mLightningEffectSystem.activate(colorTile.getCenterX(), colorTile.getCenterY(), distance, angle);
        mLightningGlitterEffectSystem.activate(targetTile.getCenterX(), targetTile.getCenterY());
    }

    private int getAngle(float distanceX, float distanceY) {
        //  dx+  | dx-
        //  dy+  | dy+
        // ------|------
        //  dx+  | dx-
        //  dy-  | dy-
        double angleInRads = Math.atan2(Math.abs(distanceY), Math.abs(distanceX));
        int angle = (int) Math.toDegrees(angleInRads);
        if (distanceX >= 0) {
            if (distanceY >= 0) {
                return 90 + angle;
            } else {
                return 90 - angle;
            }
        } else {
            if (distanceY >= 0) {
                return -90 - angle;
            } else {
                return -90 + angle;
            }
        }
    }

}
