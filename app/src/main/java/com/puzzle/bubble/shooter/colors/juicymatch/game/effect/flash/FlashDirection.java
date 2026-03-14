package com.puzzle.bubble.shooter.colors.juicymatch.game.effect.flash;


public enum FlashDirection {
    TOP,
    DOWN,
    LEFT,
    RIGHT,
    TOP_LEFT,
    TOP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT;

    public int getAngle() {
        switch (this) {
            case TOP:
                return 0;
            case DOWN:
                return 180;
            case LEFT:
                return 270;
            case RIGHT:
                return 90;
            case TOP_LEFT:
                return 315;
            case TOP_RIGHT:
                return 45;
            case DOWN_LEFT:
                return 225;
            case DOWN_RIGHT:
                return 135;
        }

        return 0;
    }

    public int getDirectionX() {
        switch (this) {
            case TOP:
            case DOWN:
                return 0;
            case LEFT:
            case TOP_LEFT:
            case DOWN_LEFT:
                return -1;
            case RIGHT:
            case TOP_RIGHT:
            case DOWN_RIGHT:
                return 1;
        }

        return 0;
    }

    public int getDirectionY() {
        switch (this) {
            case LEFT:
            case RIGHT:
                return 0;
            case TOP:
            case TOP_LEFT:
            case TOP_RIGHT:
                return -1;
            case DOWN:
            case DOWN_LEFT:
            case DOWN_RIGHT:
                return 1;
        }

        return 0;
    }

}
