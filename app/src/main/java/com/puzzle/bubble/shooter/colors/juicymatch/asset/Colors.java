package com.puzzle.bubble.shooter.colors.juicymatch.asset;

import android.content.Context;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.nativegame.natyengine.util.ResourceUtils;


public class Colors {

    public static int WHITE;
    public static int WHITE_20;

    public static int BLACK_10;
    public static int BLACK_60;
    public static int BLACK_80;

    public static void load(Context context) {
        WHITE = ResourceUtils.getColor(context, R.color.white);
        WHITE_20 = ResourceUtils.getColor(context, R.color.white_20);

        BLACK_10 = ResourceUtils.getColor(context, R.color.black_10);
        BLACK_60 = ResourceUtils.getColor(context, R.color.black_60);
        BLACK_80 = ResourceUtils.getColor(context, R.color.black_80);
    }

}
