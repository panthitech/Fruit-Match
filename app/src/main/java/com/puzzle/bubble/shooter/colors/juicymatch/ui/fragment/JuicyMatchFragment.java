package com.puzzle.bubble.shooter.colors.juicymatch.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Musics;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.game.JuicyMatch;
import com.nativegame.natyengine.Game;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameFragment;
import com.nativegame.natyengine.ui.GameView;


public class JuicyMatchFragment extends GameFragment implements View.OnClickListener {

    private Game mGame;

    public JuicyMatchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init button
        GameButton btnPause = (GameButton) view.findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);

        // Update bg music
        Musics.BG_MUSIC.setCurrentStream(false);
        Musics.BG_MUSIC.stop();
        Musics.GAME_MUSIC.setCurrentStream(true);
        Musics.GAME_MUSIC.play();

        initAd();
    }

    public void initAd() {
        ((AdManagerAdView) getView().findViewById(R.id.adView)).loadAd(new AdManagerAdRequest.Builder().build());
    }

    @Override
    protected void onViewCreated(View view) {
        mGame = new JuicyMatch(getGameActivity(), (GameView) view.findViewById(R.id.game_view), getGameActivity().getEngine());
        mGame.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGame.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGame.stop();

        // Update bg music
        Musics.GAME_MUSIC.setCurrentStream(false);
        Musics.GAME_MUSIC.stop();
        Musics.BG_MUSIC.setCurrentStream(true);
        Musics.BG_MUSIC.play();
    }

    @Override
    public boolean onBackPressed() {
        mGame.pause();
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_pause) {
            mGame.pause();
            Sounds.BUTTON_CLICK.play();
        }
    }

}
