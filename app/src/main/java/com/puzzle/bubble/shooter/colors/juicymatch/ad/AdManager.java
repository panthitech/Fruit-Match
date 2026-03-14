package com.puzzle.bubble.shooter.colors.juicymatch.ad;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

//import com.facebook.ads.Ad;
//import com.facebook.ads.RewardedVideoAd;
//import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.puzzle.bubble.shooter.colors.juicymatch.MainActivity;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.nativegame.natyengine.util.ResourceUtils;


public class AdManager {

    private final Activity mActivity;

//    private  RewardedVideoAd fbRewardedVideoAd;
    private RewardedAd mRewardedAd;
    private AdRewardListener mListener;
    private boolean mRewardEarned = false;
    public InterstitialAd interstitialAd;
    private static final String TAG = "Admanager";
    public MyActivityListener myActivityListener;

    public AdManager(Activity activity) {
        mActivity = activity;
        requestAd();
        requestInterstitialAd();
    }

    public AdRewardListener getListener() {
        return mListener;
    }

    public interface MyActivityListener {
        void showInterstitial();
    }

    public void setListener(AdRewardListener listener) {
        mListener = listener;
    }

    public void requestAd() {
        if (mRewardedAd != null) {
            return;
        }

//        fbRewardedVideoAd = new RewardedVideoAd(mActivity, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");
//
//        // RewardedVideoAd AdListener
//        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
//            @Override
//            public void onError(Ad ad, com.facebook.ads.AdError adError) {
//                Toast.makeText(mActivity, "onError", Toast.LENGTH_SHORT).show();
//                fbRewardedVideoAd = null;
////                if (!mRewardEarned) {
////                    mListener.onLossReward();
////                }
//                // Prepare next ad
//                requestAd();
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                Toast.makeText(mActivity, "onAdLoaded", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                Toast.makeText(mActivity, "onAdClicked", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                Toast.makeText(mActivity, "onLoggingImpression", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRewardedVideoCompleted() {
//                Toast.makeText(mActivity, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRewardedVideoClosed() {
//                Toast.makeText(mActivity, "onRewardedVideoClosed", Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        // loading Ad
//        fbRewardedVideoAd.loadAd(fbRewardedVideoAd.buildLoadAdConfig().withAdListener(rewardedVideoAdListener).build());

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        RewardedAd.load(mActivity, ResourceUtils.getString(mActivity, R.string.txt_admob_reward),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        mRewardedAd = null;
                        // Toast.makeText(mActivity, "Fail!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdLoaded(RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        // Toast.makeText(mActivity, "Succeed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public boolean showRewardAd() {
//        if (fbRewardedVideoAd == null) {
//            return false;
//        }
//
//        // Reset state
//        mRewardEarned = false;
//
//        if (fbRewardedVideoAd.isAdLoaded()) {
//            // showing Video Ad
//            fbRewardedVideoAd.show();
//            mListener.onEarnReward();
//            mRewardEarned = true;
//        } else {
//            // Loading Video Ad If it  is Not Loaded
//            fbRewardedVideoAd.loadAd();
//        }

        RewardedAd rewardedAd = this.mRewardedAd;
        if (rewardedAd == null) {
            return false;
        }
        this.mRewardEarned = false;
        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed
                mRewardedAd = null;
                // Check if user dismiss Ad before earn
                if (!mRewardEarned) {
                    mListener.onLossReward();
                }
                // Prepare next ad
                requestAd();
            }
        });

        mRewardedAd.show(mActivity, new OnUserEarnedRewardListener() {
            @Override
            public void onUserEarnedReward(RewardItem rewardItem) {
                mListener.onEarnReward();
                mRewardEarned = true;
                // Toast.makeText(mActivity, "Reward!", Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }

    public interface AdRewardListener {

        void onEarnReward();

        void onLossReward();

    }

    public void requestInterstitialAd() {
        AdManagerAdRequest adIRequest = new AdManagerAdRequest.Builder().build();
        InterstitialAd.load(mActivity, this.mActivity.getString(R.string.txt_admob_interstitial), adIRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        AdManager.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                super.onAdClicked();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                AdManager.this.requestInterstitialAd();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        interstitialAd = null;
                    }
                });
    }

    public boolean showInterstitialAd() {
        if (this.interstitialAd == null) {
            return false;
        }
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (interstitialAd != null) {
                    interstitialAd.show(mActivity);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        });
        return true;
//        interstitialAd.show(mActivity);
    }


    public void setInterstitialAdListener(MyActivityListener listener) {
        this.myActivityListener = listener;
    }

}
