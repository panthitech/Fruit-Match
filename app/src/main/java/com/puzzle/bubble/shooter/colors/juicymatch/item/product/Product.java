package com.puzzle.bubble.shooter.colors.juicymatch.item.product;


public class Product {

    private final String mName;
    private final int mPrice;
    private final int mDrawableId;
    private final int mButtonId;

    private String mDescription;

    public Product(String name, int price, int drawableId, int buttonId) {
        mName = name;
        mPrice = price;
        mDrawableId = drawableId;
        mButtonId = buttonId;
    }
    public String getName() {
        return mName;
    }

    public int getPrice() {
        return mPrice;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public int getButtonId() {
        return mButtonId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
