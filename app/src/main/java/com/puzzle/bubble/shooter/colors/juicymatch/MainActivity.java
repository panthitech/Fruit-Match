package com.puzzle.bubble.shooter.colors.juicymatch;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.puzzle.bubble.shooter.colors.juicymatch.ad.AdManager;
import com.puzzle.bubble.shooter.colors.juicymatch.timer.LivesTimer;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.fragment.LoadingFragment;
import com.nativegame.natyengine.ui.GameActivity;


public class MainActivity extends GameActivity {

    private AdManager mAdManager;
    private LivesTimer mLivesTimer;
    private static RewardedVideoAd fbRewardedVideoAd;

    public AdManager getAdManager() {
        return mAdManager;
    }

    public LivesTimer getLivesTimer() {
        return mLivesTimer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_JuicyMatch);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_main);
        setFragmentContainer(R.id.layout_container);

        mAdManager = new AdManager(this);
        mLivesTimer = new LivesTimer(this);

        // Show the menu fragment
        if (savedInstanceState == null) {
            navigateToFragment(new LoadingFragment());
        }

//        loadRewardedVideoAd();

    }

    void loadRewardedVideoAd() {
        // initializing RewardedVideoAd Object
        // RewardedVideoAd  Constructor Takes 2 Arguments
        // 1)Context
        // 2)Placement Id
        fbRewardedVideoAd = new RewardedVideoAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");

        // RewardedVideoAd AdListener
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Toast.makeText(MainActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                Toast.makeText(MainActivity.this, "onAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Toast.makeText(MainActivity.this, "onLoggingImpression", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoCompleted() {
                Toast.makeText(MainActivity.this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoClosed() {
                Toast.makeText(MainActivity.this, "onRewardedVideoClosed", Toast.LENGTH_SHORT).show();
            }
        };

        // loading Ad
        fbRewardedVideoAd.loadAd(fbRewardedVideoAd.buildLoadAdConfig().withAdListener(rewardedVideoAdListener).build());
    }

    public static void showRewardedVideoAd() {
        // Checking If Ad is Loaded
        // or Not
        if (fbRewardedVideoAd.isAdLoaded()) {
            // showing Video Ad
            fbRewardedVideoAd.show();
        } else {
            // Loading Video Ad If it  is Not Loaded
            fbRewardedVideoAd.loadAd();
        }
    }

}
