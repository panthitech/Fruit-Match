package com.puzzle.bubble.shooter.colors.juicymatch.game.counter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.puzzle.bubble.shooter.colors.juicymatch.MainActivity;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.ad.AdManager;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.database.DatabaseHelper;
import com.puzzle.bubble.shooter.colors.juicymatch.game.GameEvent;
import com.puzzle.bubble.shooter.colors.juicymatch.game.booster.BombController;
import com.puzzle.bubble.shooter.colors.juicymatch.game.booster.BoosterController;
import com.puzzle.bubble.shooter.colors.juicymatch.game.booster.GloveController;
import com.puzzle.bubble.shooter.colors.juicymatch.game.booster.HammerController;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.tile.TileSystem;
import com.puzzle.bubble.shooter.colors.juicymatch.item.Item;
import com.puzzle.bubble.shooter.colors.juicymatch.item.prize.Prize;
import com.puzzle.bubble.shooter.colors.juicymatch.item.prize.PrizeManager;
import com.puzzle.bubble.shooter.colors.juicymatch.item.product.Product;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.puzzle.bubble.shooter.colors.juicymatch.level.TutorialType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.runnable.RunnableEntity;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.BuyHammerDialog;
import com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog.ErrorDialog;

import java.util.List;


public class BoosterCounter extends RunnableEntity implements View.OnClickListener, AdManager.AdRewardListener,
        EventListener, BoosterController.BoosterListener {

    private static final String SIGN_INFINITE = "∞";
    private static final int INFINITE = -1;

    private final HammerController mHammerController;
    private final BombController mBombController;
    private final GloveController mGloveController;

    private BoosterState mState;
    private int mHammerCount;
    private int mBombCount;
    private int mGloveCount;
    private boolean mIsEnable = false;
    private boolean mIsAddBooster = false;
    private boolean mIsRemoveBooster = false;
    DatabaseHelper databaseHelper;
    TutorialType tutorialType;
    int pos = 0;

    @Override
    public void onEarnReward() {
        if (pos == 1) {
            mState = BoosterState.HAMMER;
            mIsAddBooster = true;
            Toast.makeText(mActivity, "NOW HAMMER ADDDED..!!", Toast.LENGTH_SHORT).show();

//            showPrize(1);
        } else if (pos == 2) {
            showPrize(2);
        } else {
            showPrize(3);
        }
    }

    @Override
    public void onLossReward() {

    }


    private enum BoosterState {
        WAITING,
        HAMMER,
        BOMB,
        GLOVE
    }

    public BoosterCounter(GameActivity activity, Engine engine, TileSystem tileSystem) {
        super(activity, engine);

        // Init booster controller
        mHammerController = new HammerController(engine, tileSystem);
        mBombController = new BombController(engine, tileSystem);
        mGloveController = new GloveController(engine, tileSystem);
        mHammerController.setListener(this);
        mBombController.setListener(this);
        mGloveController.setListener(this);

        activity = new GameActivity();
        updateText();

        // Init booster count
        databaseHelper = DatabaseHelper.getInstance(activity);
        tutorialType = Level.LEVEL_DATA.getTutorialType();   // Check is current level has Tutorial
        mHammerCount = tutorialType == TutorialType.HAMMER ? INFINITE : databaseHelper.getItemCount(Item.HAMMER);
        mBombCount = tutorialType == TutorialType.BOMB ? INFINITE : databaseHelper.getItemCount(Item.BOMB);
        mGloveCount = tutorialType == TutorialType.GLOVE ? INFINITE : databaseHelper.getItemCount(Item.GLOVE);

        // Init booster button
        GameButton btnHammer = (GameButton) mActivity.findViewById(R.id.btn_hammer);
        btnHammer.setOnClickListener(this);
        GameButton btnBomb = (GameButton) mActivity.findViewById(R.id.btn_bomb);
        btnBomb.setOnClickListener(this);
        GameButton btnGlove = (GameButton) mActivity.findViewById(R.id.btn_glove);
        btnGlove.setOnClickListener(this);

        // Init booster text

    }

    @Override
    public void onStart() {
        mState = BoosterState.WAITING;
        updateText();
        setPostRunnable(true);
    }


    @Override
    public void onUpdate(long elapsedMillis) {
        if (mIsAddBooster) {
            updateText();
            addBooster();
            dispatchEvent(GameEvent.ADD_BOOSTER);
            mIsAddBooster = false;
        }

        if (mIsRemoveBooster) {
            updateText();
            removeBooster();
            dispatchEvent(GameEvent.REMOVE_BOOSTER);
            mState = BoosterState.WAITING;
            mIsRemoveBooster = false;
        }
    }

    @Override
    protected void onUpdateRunnable() {
        updateText();
        unLockButton();
    }

    @Override
    public void onClick(View view) {
        synchronized (this) {
            if (!mIsEnable) {
                return;
            }
            if (mState == BoosterState.WAITING) {
                int id = view.getId();
                if (id == R.id.btn_hammer) {
                    if (mHammerCount == 0) {
//                        pos = 1;
//                        buyHammerDialog();
                        return;
                    }
                    mState = BoosterState.HAMMER;
                } else if (id == R.id.btn_bomb) {
                    if (mBombCount == 0) {
                        pos = 2;
                        return;
                    }
                    mState = BoosterState.BOMB;
                } else if (id == R.id.btn_glove) {
                    if (mGloveCount == 0) {
                        pos = 3;
                        return;
                    }
                    mState = BoosterState.GLOVE;
                }
                lockButton();
                mIsAddBooster = true;
            } else {
                unLockButton();
                mIsRemoveBooster = true;
            }
            Sounds.BUTTON_CLICK.play();
        }
    }

    private void showPrize(int pos) {
        mHammerCount = tutorialType == TutorialType.HAMMER ? INFINITE : databaseHelper.getItemCount(Item.HAMMER);
        Prize prize = getPrize(pos);
        savePrizes(prize.getName(), prize.getCount());
        updateCoin();
        updateText();
    }


    private Prize getPrize(int pos) {
        PrizeManager prizeManager = new PrizeManager();
        if (pos == 1) {
            return prizeManager.getPrize(PrizeManager.PRIZE_HAMMER);
        } else if (pos == 2) {
            return prizeManager.getPrize(PrizeManager.PRIZE_BOMB);
        } else {
            return prizeManager.getPrize(PrizeManager.PRIZE_GLOVE);
        }
    }


    private void showRewardedAd() {
        AdManager adManager = ((MainActivity) mActivity).getAdManager();
        adManager.setListener(this);
        boolean isConnect = adManager.showRewardAd();
        if (isConnect) {
            mEngine.pause();
            updateText();
        } else {
            updateText();
            ErrorDialog dialog = new ErrorDialog(mActivity) {
                @Override
                public void retry() {
                    adManager.requestAd();
                    showRewardedAd();
                }

                @Override
                public void quit() {
                    dispatchEvent(GameEvent.GAME_OVER);
                }
            };
            mActivity.showDialog(dialog);
            return;
        }
    }

    private void buyHammerDialog() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BuyHammerDialog buyHammerDialog = new BuyHammerDialog(mActivity) {
                    @Override
                    public void showAd() {
                        showRewardedAd();
                        updateText();
                    }

                    @Override
                    public void quit() {
//                        dispatchEvent(GameEvent.GAME_OVER);
                        updateText();
                    }
                };
                mActivity.showDialog(buyHammerDialog);
            }
        });
        updateText();
    }

    private void savePrizes(String name, int count) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mActivity);
        int saving = databaseHelper.getItemCount(name);
        databaseHelper.updateItemCount(name, saving + count);
    }


    public void updateCoin() {
    }

    @Override
    public void onEvent(Event event) {
        switch ((GameEvent) event) {
            case START_GAME:
            case STOP_COMBO:
            case ADD_EXTRA_MOVES:
                mIsEnable = true;
                break;
            case PLAYER_SWAP:
                mIsEnable = false;
                break;
            case GAME_WIN:
            case GAME_OVER:
                removeFromGame();
                break;
        }
    }

    @Override
    public void onConsumeBooster() {
        switch (mState) {
            case HAMMER:
                updateText();
                if (mHammerCount != INFINITE) {
                    mHammerCount--;
                    DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mActivity);
                    databaseHelper.updateItemCount(Item.HAMMER, mHammerCount);
                }
                break;
            case BOMB:
                updateText();
                if (mBombCount != INFINITE) {
                    mBombCount--;
                    DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mActivity);
                    databaseHelper.updateItemCount(Item.BOMB, mBombCount);
                }
                break;
            case GLOVE:
                updateText();
                if (mGloveCount != INFINITE) {
                    mGloveCount--;
                    DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mActivity);
                    databaseHelper.updateItemCount(Item.GLOVE, mGloveCount);
                }
                break;
        }
        updateText();
        setPostRunnable(true);
        mState = BoosterState.WAITING;
        mIsEnable = false;
    }

    private void addBooster() {
        switch (mState) {
            case HAMMER:
                mHammerController.addToGame();
                break;
            case BOMB:
                mBombController.addToGame();
                break;
            case GLOVE:
                mGloveController.addToGame();
                break;
        }
    }

    private void removeBooster() {
        switch (mState) {
            case HAMMER:
                mHammerController.removeFromGame();
                break;
            case BOMB:
                mBombController.removeFromGame();
                break;
            case GLOVE:
                mGloveController.removeFromGame();
                break;
        }
    }

    private void lockButton() {
        GameButton btnHammer = (GameButton) mActivity.findViewById(R.id.btn_hammer);
        GameButton btnBomb = (GameButton) mActivity.findViewById(R.id.btn_bomb);
        GameButton btnGlove = (GameButton) mActivity.findViewById(R.id.btn_glove);
        switch (mState) {
            case HAMMER:
                btnGlove.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnGlove.setEnabled(false);

                btnBomb.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnBomb.setEnabled(false);
                break;
            case BOMB:
                btnHammer.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnHammer.setEnabled(false);

                btnGlove.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnGlove.setEnabled(false);
                break;
            case GLOVE:
                btnHammer.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnHammer.setEnabled(false);

                btnBomb.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnBomb.setEnabled(false);
                break;
        }
    }

    private void unLockButton() {
        GameButton btnHammer = (GameButton) mActivity.findViewById(R.id.btn_hammer);
        btnHammer.removeColorFilter();
        btnHammer.setEnabled(true);

        GameButton btnBomb = (GameButton) mActivity.findViewById(R.id.btn_bomb);
        btnBomb.removeColorFilter();
        btnBomb.setEnabled(true);

        GameButton btnGlove = (GameButton) mActivity.findViewById(R.id.btn_glove);
        btnGlove.removeColorFilter();
        btnGlove.setEnabled(true);
    }

    private void updateText() {
        TextView txtHammer = (TextView) mActivity.findViewById(R.id.txt_hammer);
        txtHammer.setText(mHammerCount == INFINITE ? SIGN_INFINITE : String.valueOf(mHammerCount));

        TextView txtBomb = (TextView) mActivity.findViewById(R.id.txt_bomb);
        txtBomb.setText(mBombCount == INFINITE ? SIGN_INFINITE : String.valueOf(mBombCount));

        TextView txtGlove = (TextView) mActivity.findViewById(R.id.txt_gloves);
        txtGlove.setText(mGloveCount == INFINITE ? SIGN_INFINITE : String.valueOf(mGloveCount));
    }

}
