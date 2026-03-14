package com.puzzle.bubble.shooter.colors.juicymatch.item.prize;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.item.Item;

import java.util.HashMap;
import java.util.Map;


public class PrizeManager {

    private final Map<String, Prize> mPrizeMap = new HashMap<>();

    public static final String PRIZE_COIN_50 = "prize_coin_50";
    public static final String PRIZE_COIN_150 = "prize_coin_150";
    public static final String PRIZE_GLOVE = "prize_glove";
    public static final String PRIZE_BOMB = "prize_bomb";
    public static final String PRIZE_HAMMER = "prize_hammer";

    public PrizeManager() {
        // Init all the prize
        Prize prizeCoin50 = new Prize(Item.COIN, 50, R.drawable.ui_coin_50);
        Prize prizeCoin150 = new Prize(Item.COIN, 150, R.drawable.ui_coin_150);
        Prize prizeGlove = new Prize(Item.GLOVE, 1, R.drawable.ui_booster_glove);
        Prize prizeBomb = new Prize(Item.BOMB, 1, R.drawable.ui_booster_bomb);
        Prize prizeHammer = new Prize(Item.HAMMER, 1, R.drawable.ui_booster_hammer);

        // Add prize to map
        mPrizeMap.put(PRIZE_COIN_50, prizeCoin50);
        mPrizeMap.put(PRIZE_COIN_150, prizeCoin150);
        mPrizeMap.put(PRIZE_GLOVE, prizeGlove);
        mPrizeMap.put(PRIZE_BOMB, prizeBomb);
        mPrizeMap.put(PRIZE_HAMMER, prizeHammer);
    }
    public Prize getPrize(String key) {
        return mPrizeMap.get(key);
    }

}
