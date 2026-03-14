package com.puzzle.bubble.shooter.colors.juicymatch.asset;

import android.content.Context;
import android.graphics.Typeface;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.nativegame.natyengine.util.ResourceUtils;


public class Fonts {

    public static Typeface BALOO;

    public static void load(Context context) {
        BALOO = ResourceUtils.getFont(context, R.font.baloo);
    }

}
