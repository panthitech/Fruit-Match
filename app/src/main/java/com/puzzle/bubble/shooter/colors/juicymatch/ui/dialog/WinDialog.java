package com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.nativegame.natyengine.ui.GameActivity;


public class WinDialog extends BaseDialog {

    private static final long TIME_TO_LIVE = 1500;

    public WinDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.dialog_win);
        setContainerView(R.layout.dialog_container_game);
        setEnterAnimationId(R.anim.enter_from_top);
        setExitAnimationId(R.anim.exit_to_bottom);

        // Dismiss the dialog after 1500ms
        getContentView().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, TIME_TO_LIVE);
    }

}
