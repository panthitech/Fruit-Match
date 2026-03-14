package com.puzzle.bubble.shooter.colors.juicymatch.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.puzzle.bubble.shooter.colors.juicymatch.MainActivity;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Musics;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Preferences;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.ExitDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.SettingDialog;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameFragment;
import com.nativegame.natyengine.ui.GameImage;


public class MenuFragment extends GameFragment implements View.OnClickListener {


    public MenuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init logo image
        GameImage imageLogo = (GameImage) view.findViewById(R.id.image_logo);
        imageLogo.popUp(1000, 300);
        Animation scaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.logo_pulse);
        imageLogo.startAnimation(scaleAnimation);

        GameImage imageLogoBg = (GameImage) view.findViewById(R.id.image_logo_bg);
        imageLogoBg.popUp(300, 300);
        Animation rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.logo_rotate);
        imageLogoBg.startAnimation(rotateAnimation);

        // Init button
        GameButton btnPlay = (GameButton) view.findViewById(R.id.btn_start);
        btnPlay.popUp(200, 600);
        btnPlay.setOnClickListener(this);
        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.button_pulse);
        btnPlay.startAnimation(pulseAnimation);

        GameButton btnSetting = (GameButton) view.findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);

        // Init audio state from Preference
        boolean musicEnable = Preferences.PREF_SETTING.getBoolean(Preferences.KEY_MUSIC, true);
        boolean soundEnable = Preferences.PREF_SETTING.getBoolean(Preferences.KEY_SOUND, true);
        getGameActivity().getMusicManager().setAudioEnable(musicEnable);
        getGameActivity().getSoundManager().setAudioEnable(soundEnable);

        // Play bg music
        Musics.BG_MUSIC.play();

        initAd();


    }

    public void initAd() {
        ((AdManagerAdView) getView().findViewById(R.id.adView)).loadAd(new AdManagerAdRequest.Builder().build());
    }

    @Override
    public boolean onBackPressed() {
        showExitDialog();
        return true;
    }

    @Override
    public void onClick(View view) {
        Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_start) {
            getGameActivity().navigateToFragment(new MapFragment());
        } else if (id == R.id.btn_setting) {
            showSettingDialog();
        }
    }
    private void showExitDialog() {
        ExitDialog exitDialog = new ExitDialog(getGameActivity()) {
            @Override
            public void exit() {
                getGameActivity().finish();
            }
        };
        getGameActivity().showDialog(exitDialog);
    }

    private void showSettingDialog() {
        SettingDialog settingDialog = new SettingDialog(getGameActivity());
        getGameActivity().showDialog(settingDialog);
    }



}
