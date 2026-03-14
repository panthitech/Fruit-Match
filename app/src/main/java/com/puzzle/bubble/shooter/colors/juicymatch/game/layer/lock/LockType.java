package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.lock;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.nativegame.natyengine.texture.Texture;


public enum LockType {
    CENTER;

    public Texture getTexture() {
        switch (this) {
            case CENTER:
                return Textures.LOCK;
            default:
                throw new IllegalArgumentException("Lock Texture not found!");
        }
    }

}
