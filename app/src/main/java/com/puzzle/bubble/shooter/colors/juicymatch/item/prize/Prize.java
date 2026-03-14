package com.puzzle.bubble.shooter.colors.juicymatch.item.prize;


public class Prize {

    private final String mName;
    private final int mCount;
    private final int mDrawableId;

    public Prize(String name, int count, int drawableId) {
        mName = name;
        mCount = count;
        mDrawableId = drawableId;
    }
    public String getName() {
        return mName;
    }

    public int getCount() {
        return mCount;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

}
