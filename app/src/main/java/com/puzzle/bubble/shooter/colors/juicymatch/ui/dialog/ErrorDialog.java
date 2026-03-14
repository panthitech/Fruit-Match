package com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog;

import android.view.View;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameText;


public class ErrorDialog extends BaseDialog implements View.OnClickListener {

    private int mSelectedId = R.id.btn_cancel;

    public ErrorDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.dialog_error);
        setContainerView(R.layout.dialog_container);
        setEnterAnimationId(R.anim.enter_from_center);
        setExitAnimationId(R.anim.exit_to_center);

        // Init text
        GameText txtError = (GameText) findViewById(R.id.txt_error);
        txtError.popUp(200, 300);

        // Init button
        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        GameButton btnRetry = (GameButton) findViewById(R.id.btn_retry);
        btnRetry.popUp(200, 500);
        btnRetry.setOnClickListener(this);
    }
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_cancel) {
            quit();
        } else if (mSelectedId == R.id.btn_retry) {
            retry();
        }
    }

    @Override
    public void onClick(View view) {
        Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            mSelectedId = id;
            dismiss();
        } else if (id == R.id.btn_retry) {
            mSelectedId = id;
            dismiss();
        }
    }
    public void quit() {
    }

    public void retry() {
    }

}
