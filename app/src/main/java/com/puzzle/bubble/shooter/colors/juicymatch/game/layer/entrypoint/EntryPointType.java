package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.entrypoint;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.nativegame.natyengine.texture.Texture;


public enum EntryPointType {
    ARROW;

    public Texture getTexture() {
        switch (this) {
            case ARROW:
                return Textures.ARROW;
            default:
                throw new IllegalArgumentException("EntryPoint Texture not found!");
        }
    }

}
