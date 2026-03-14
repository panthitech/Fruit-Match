package com.puzzle.bubble.shooter.colors.juicymatch.asset;

import android.content.Context;

import com.nativegame.natyengine.util.storage.preference.Preference;


public class Preferences {

    public static final String PREFS_SETTING_NAME = "prefs_setting";
    public static final String KEY_SOUND = "sound";
    public static final String KEY_MUSIC = "music";
    public static final String KEY_HINT = "hint";
    public static final String KEY_LAST_PLAY_TIME = "last_play_time";

    public static final String PREFS_LIVES_NAME = "prefs_lives";
    public static final String KEY_LIVES = "lives";
    public static final String KEY_MILLIS_LEFT = "millis_left";
    public static final String KEY_END_TIME = "end_time";

    public static Preference PREF_SETTING;
    public static Preference PREF_LIVES;

    public static void load(Context context) {
        PREF_SETTING = new Preference(context, PREFS_SETTING_NAME);
        PREF_LIVES = new Preference(context, PREFS_LIVES_NAME);
    }

}
