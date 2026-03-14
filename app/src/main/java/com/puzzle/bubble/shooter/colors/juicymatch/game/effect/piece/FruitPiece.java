package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.piece;


public enum FruitPiece {
    LEFT,
    RIGHT;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getIndex() {
        switch (this) {
            case LEFT:
                return 0;
            case RIGHT:
                return 1;
            default:
                throw new IllegalArgumentException("FruitPiece index not found!");
        }
    }

    public int getDirection() {
        switch (this) {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                throw new IllegalArgumentException("FruitPiece direction not found!");
        }
    }

}
