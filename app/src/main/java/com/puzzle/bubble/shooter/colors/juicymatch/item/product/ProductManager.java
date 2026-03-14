package com.puzzle.bubble.shooter.colors.juicymatch.item.product;

import android.app.Activity;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.item.Item;
import com.nativegame.natyengine.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;


public class ProductManager {

    private final List<Product> mProducts = new ArrayList<>();

    public ProductManager(Activity activity) {
        // Init all the product
        Product productWatchAd = new Product(Item.COIN, 0, R.drawable.ui_coin_50, R.drawable.ui_btn_watch_ad);
        Product productGlove = new Product(Item.GLOVE, 50, R.drawable.ui_booster_glove, R.drawable.ui_btn_price_50);
        Product productBomb = new Product(Item.BOMB, 60, R.drawable.ui_booster_bomb, R.drawable.ui_btn_price_60);
        Product productHammer = new Product(Item.HAMMER, 70, R.drawable.ui_booster_hammer, R.drawable.ui_btn_price_70);

        // Init product description
        productWatchAd.setDescription(ResourceUtils.getString(activity, R.string.txt_coin));
        productGlove.setDescription(ResourceUtils.getString(activity, R.string.txt_glove));
        productBomb.setDescription(ResourceUtils.getString(activity, R.string.txt_bomb));
        productHammer.setDescription(ResourceUtils.getString(activity, R.string.txt_hammer));

        // Add product to list
        mProducts.add(productWatchAd);
        mProducts.add(productGlove);
        mProducts.add(productBomb);
        mProducts.add(productHammer);
    }
    public List<Product> getAllProducts() {
        return mProducts;
    }

}
