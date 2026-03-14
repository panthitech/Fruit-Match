package com.puzzle.bubble.shooter.colors.juicymatch.asset;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.nativegame.natyengine.audio.music.Music;
import com.nativegame.natyengine.audio.music.MusicManager;


public class Musics {

    public static Music BG_MUSIC;
    public static Music GAME_MUSIC;

    public static void load(MusicManager musicManager) {
        BG_MUSIC = musicManager.load(R.raw.happy_and_joyful_children);
        BG_MUSIC.setVolume(0.3f, 0.3f);
        BG_MUSIC.setLooping(true);
        BG_MUSIC.setCurrentStream(true);

        GAME_MUSIC = musicManager.load(R.raw.bgm);
        GAME_MUSIC.setVolume(1f, 1f);
        GAME_MUSIC.setLooping(true);
        GAME_MUSIC.setCurrentStream(false);
    }

}
