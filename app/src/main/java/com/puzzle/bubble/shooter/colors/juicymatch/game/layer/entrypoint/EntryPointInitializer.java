package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.entrypoint;


public class EntryPointInitializer {

    public static EntryPointType getType(char c) {
        switch (c) {
            case 'A':
                return EntryPointType.ARROW;
            default:
                throw new IllegalArgumentException("EntryPointType not found!");
        }
    }

}
