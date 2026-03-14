package com.puzzle.bubble.shooter.colors.juicymatch;


import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;


public class GameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);
    }
}
