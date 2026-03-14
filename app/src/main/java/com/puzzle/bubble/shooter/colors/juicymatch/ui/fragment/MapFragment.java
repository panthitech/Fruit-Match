package com.puzzle.bubble.shooter.colors.juicymatch.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.puzzle.bubble.shooter.colors.juicymatch.MainActivity;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.database.DatabaseHelper;
import com.puzzle.bubble.shooter.colors.juicymatch.item.Item;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.puzzle.bubble.shooter.colors.juicymatch.timer.LivesTimer;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.LevelDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.MoreCoinDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.MoreLivesDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.SettingDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.ShopDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.WheelDialog;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameFragment;

import java.util.List;


public class MapFragment extends GameFragment implements View.OnClickListener {

    private static final int TOTAL_LEVEL = 100;

    private LivesTimer mLivesTimer;
    public List<Integer> mLevelStar;
    public DatabaseHelper mDatabaseHelper;
    private int mCurrentLevel;


    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLivesTimer = ((MainActivity) getGameActivity()).getLivesTimer();

        // Init button
        GameButton btnSetting = (GameButton) view.findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);

        GameButton btnShop = (GameButton) view.findViewById(R.id.btn_shop);
        btnShop.setOnClickListener(this);

        GameButton btnWheel = (GameButton) view.findViewById(R.id.btn_wheel);
        btnWheel.setOnClickListener(this);
        Animation rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.logo_rotate);
        btnWheel.startAnimation(rotateAnimation);

        GameButton btnLives = (GameButton) view.findViewById(R.id.btn_lives);
        btnLives.setOnClickListener(this);

        GameButton btnCoin = (GameButton) view.findViewById(R.id.btn_coin);
        btnCoin.setOnClickListener(this);

        // Init level button and star
        mDatabaseHelper = DatabaseHelper.getInstance(getContext());
        this.mLevelStar = this.mDatabaseHelper.getAllLevelStars();
        this.mCurrentLevel = this.mLevelStar.size() + 1;

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public synchronized void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                    if (mCurrentLevel <= 100) {
                        ScrollView scrollView = (ScrollView) getView().findViewById(R.id.layout_map);
                        scrollView.scrollTo(0, ((TextView) findViewByName("btn_level_" + mCurrentLevel)).getBottom() - (scrollView.getHeight() / 2));
                    }
                }
            }
        });

        initAd();


//        mCurrentLevel = databaseHelper.getAllLevelStars().size() + 1;
//        if (mCurrentLevel > TOTAL_LEVEL) {
//            mCurrentLevel = TOTAL_LEVEL;
//        }
//        mCurrentPage = (int) Math.ceil(mCurrentLevel * 1.0d / LEVEL_PRE_PAGE);
//        updatePage(mCurrentPage);
        loadCoin();
        initLevelButton();
        initLevelStar();

        // Show current level dialog
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLevelDialog(mCurrentLevel);
            }
        }, 800);
    }

    public View findViewByName(String name) {
        return getView().findViewById(getResources().getIdentifier(name, "id", getGameActivity().getPackageName()));
    }

    public void initAd() {
        ((AdManagerAdView) getView().findViewById(R.id.adView)).loadAd(new AdManagerAdRequest.Builder().build());
    }

    @Override
    public void onResume() {
        super.onResume();
        mLivesTimer.startTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLivesTimer.stopTimer();
    }

    @Override
    public boolean onBackPressed() {
        getGameActivity().navigateToFragment(new MenuFragment());
        return true;
    }

    @Override
    public void onClick(View view) {
        Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_shop) {
            showShopDialog();
        } else if (id == R.id.btn_wheel) {
            showWheelDialog();
        } else if (id == R.id.btn_coin) {
            showMoreCoinDialog();
        } else if (id == R.id.btn_lives) {
            if (mLivesTimer.getLivesCount() < LivesTimer.MAX_LIVES) {
                showMoreLivesDialog();
            } else {
                Toast.makeText(getGameActivity(), "Your Fruit Match Lives Are Full. !! Enjoy Game..", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btn_setting) {
            showSettingDialog();
        }
    }
//    private void updatePage(int page) {
//        // Update page number text
//        TextView currentPage = (TextView) getView().findViewById(R.id.txt_current_page);
//        currentPage.setText(String.valueOf(page));
//
//        TextView previousPage = (TextView) getView().findViewById(R.id.txt_previous_page);
//        previousPage.setText(page == 1 ? "" : String.valueOf(page - 1));
//
//        TextView nextPage = (TextView) getView().findViewById(R.id.txt_next_page);
//        nextPage.setText(page == MAX_PAGE ? "" : String.valueOf(page + 1));
//
//        // Update level button and star
//        loadButton(page);
//        loadStar(page);
//    }

    public void initLevelButton() {
        for (int i = 1; i <= 100; i++) {
            TextView txtLevel = (TextView) findViewByName("btn_level_" + i);
            if (i <= this.mCurrentLevel) {
                final int level = i;
                txtLevel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MapFragment.this.showLevelDialog(level);
                        Sounds.BUTTON_CLICK.play();
                    }
                });
                txtLevel.setBackgroundResource(R.drawable.btn_level);
                txtLevel.setTextColor(getResources().getColor(R.color.brown));
                UIEffect.createButtonEffect(txtLevel);
            } else {
                txtLevel.setBackgroundResource(R.drawable.btn_level_lock);
                txtLevel.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    public void initLevelStar() {
        for (int i = 1; i <= 100; i++) {
            ImageView imageStar = (ImageView) findViewByName("image_level_star_" + i);
            if (i < this.mCurrentLevel) {
                switch (this.mLevelStar.get(i - 1).intValue()) {
                    case 1:
                        imageStar.setImageResource(R.drawable.ui_star_set_01);
                        break;
                    case 2:
                        imageStar.setImageResource(R.drawable.ui_star_set_02);
                        break;
                    case 3:
                        imageStar.setImageResource(R.drawable.ui_star_set_03);
                        break;
                }
                imageStar.setVisibility(View.VISIBLE);
            } else {
                imageStar.setVisibility(View.INVISIBLE);
            }
        }
    }

//    private void loadButton(int page) {
//        int increment = (page - 1) * 20;
//
//        for (int i = 1; i <= LEVEL_PRE_PAGE; i++) {
//
//            // Init level button
//            String name = "btn_level_" + i;
//            int id = getResources().getIdentifier(name, "id", getGameActivity().getPackageName());
//            GameText txtLevel = (GameText) getView().findViewById(id);
//
//            int level = i + increment;
//            if (level <= mCurrentLevel) {
//                txtLevel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Sounds.BUTTON_CLICK.play();
//                        showLevelDialog(level);
//                    }
//                });
//                txtLevel.setBackgroundResource(R.drawable.ui_btn_level);
//                txtLevel.setEnabled(true);
//            } else {
//                txtLevel.setOnClickListener(null);
//                txtLevel.setBackgroundResource(R.drawable.ui_btn_level_lock);
//                txtLevel.setEnabled(false);
//            }
//            txtLevel.setText(String.valueOf(level));
//            txtLevel.popUp(200, i * 30);
//        }
//    }

//    private void loadStar(int page) {
//        int increment = (page - 1) * 20;
//        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());
//        List<Integer> stars = databaseHelper.getAllLevelStars();
//
//        for (int i = 1; i <= LEVEL_PRE_PAGE; i++) {
//
//            // Init level star
//            String name = "image_level_star_" + i;
//            int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
//            GameImage imageStar = (GameImage) getView().findViewById(id);
//
//            int level = i + increment;
//            if (level < mCurrentLevel) {
//                int star = stars.get(level - 1);
//                switch (star) {
//                    case 1:
//                        imageStar.setBackgroundResource(R.drawable.ui_star_set_01);
//                        break;
//                    case 2:
//                        imageStar.setBackgroundResource(R.drawable.ui_star_set_02);
//                        break;
//                    case 3:
//                        imageStar.setBackgroundResource(R.drawable.ui_star_set_03);
//                        break;
//                }
//                imageStar.setVisibility(View.VISIBLE);
//            } else {
//                imageStar.setVisibility(View.INVISIBLE);
//            }
//            imageStar.popUp(200, i * 40);
//        }
//    }

    private void loadCoin() {
        TextView textCoin = (TextView) getView().findViewById(R.id.txt_coin);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());
        int coin = databaseHelper.getItemCount(Item.COIN);
        textCoin.setText(String.valueOf(coin));
    }

    private void showLevelDialog(int level) {
        // We load level data here before starting game
        Level.load(getContext(), level);
        LevelDialog levelDialog = new LevelDialog(getGameActivity()) {
            @Override
            public void startGame() {
                // Check is player lives enough
                if (mLivesTimer.getLivesCount() > 0) {
                    getGameActivity().navigateToFragment(new JuicyMatchFragment());
                } else {
                    showMoreLivesDialog();
                }
            }
        };
        getGameActivity().showDialog(levelDialog);
    }

    private void showShopDialog() {
        ShopDialog shopDialog = new ShopDialog(getGameActivity()) {
            @Override
            public void updateCoin() {
                loadCoin();
            }
        };
        getGameActivity().showDialog(shopDialog);
    }

    private void showWheelDialog() {
        WheelDialog wheelDialog = new WheelDialog(getGameActivity()) {
            @Override
            public void updateCoin() {
                loadCoin();
            }
        };
        getGameActivity().showDialog(wheelDialog);
    }

    private void showMoreCoinDialog() {
        MoreCoinDialog moreCoinDialog = new MoreCoinDialog(getGameActivity()) {
            @Override
            public void updateCoin() {
                loadCoin();
            }
        };
        getGameActivity().showDialog(moreCoinDialog);
    }

    private void showMoreLivesDialog() {
        MoreLivesDialog moreLivesDialog = new MoreLivesDialog(getGameActivity());
        getGameActivity().showDialog(moreLivesDialog);
    }

    private void showSettingDialog() {
        SettingDialog settingDialog = new SettingDialog(getGameActivity());
        getGameActivity().showDialog(settingDialog);
    }

}
