package com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog;

import android.view.View;

import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;


public class BuyHammerDialog extends BaseDialog implements View.OnClickListener {

    private int mSelectedId = R.id.btn_cancel;

    public BuyHammerDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.dialog_buy_hammer);
        setContainerView(R.layout.dialog_container);
        setEnterAnimationId(R.anim.enter_from_center);
        setExitAnimationId(R.anim.exit_to_center);

        // Init image
        GameImage imageExtraMove = (GameImage) findViewById(R.id.image_extra_move);
        imageExtraMove.popUp(200, 300);

        // Init text
        GameText txtExtraMove = (GameText) findViewById(R.id.txt_extra_move);
        txtExtraMove.popUp(200, 500);

        // Init button
        GameButton btnWatchAd = (GameButton) findViewById(R.id.btn_watch_ad);
        btnWatchAd.popUp(200, 700);
        btnWatchAd.setOnClickListener(this);

        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_watch_ad) {
            showAd();
        } else if (mSelectedId == R.id.btn_cancel) {
            quit();
        }
    }

    @Override
    public void onClick(View view) {
        Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            mSelectedId = id;
            dismiss();
        } else if (id == R.id.btn_watch_ad) {
            mSelectedId = id;
            dismiss();
        }
    }

    public void showAd() {
    }

    public void quit() {
    }

}
