package com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.database.DatabaseHelper;
import com.puzzle.bubble.shooter.colors.juicymatch.level.Level;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.util.ResourceUtils;


public class ScoreDialog extends BaseDialog implements View.OnClickListener {

    public ScoreDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.dialog_score);
        setContainerView(R.layout.dialog_container_game);
        setEnterAnimationId(R.anim.enter_from_left);
        setExitAnimationId(android.R.anim.fade_out);

        // Init text
        TextView txtLevel = (TextView) findViewById(R.id.txt_level);
        txtLevel.setText(ResourceUtils.getString(activity, R.string.txt_level, Level.LEVEL_DATA.getLevel()));

        // Init button
        GameButton button = (GameButton) findViewById(R.id.btn_next);
        button.setOnClickListener(this);
        button.popUp(300, 600);

        initScore();
        initStar();
        insertOrUpdateStar();

        ImageView imageView = (ImageView) findViewById(R.id.fireworksview);
        Glide.with(mParent)
                .asGif()
                .load(R.drawable.fireworksimg)
                .into(new DrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (resource instanceof GifDrawable) {
                            GifDrawable gifDrawable = (GifDrawable) resource;
                            gifDrawable.setLoopCount(GifDrawable.LOOP_FOREVER);
                            gifDrawable.start();
                        }
                    }
                }.getView());
    }

    @Override
    protected void onDismiss() {
        mParent.navigateBack();
    }

    @Override
    public void onClick(View view) {
        Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_next) {
            dismiss();
        }
    }

    private void initScore() {
        int score = Level.LEVEL_DATA.getScore();

        // Update score text
        TextView txtScore = (TextView) findViewById(R.id.txt_final_score);
        ValueAnimator animator = ValueAnimator.ofFloat(score - 150, score);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                txtScore.setText(String.valueOf((int) value));
            }
        });
        animator.start();
        Sounds.ADD_SCORE.play();
    }

    private void initStar() {
        int star = Level.LEVEL_DATA.getStar();

        // Update star image
        StarAnimationListener listener = new StarAnimationListener();
        if (star >= 1) {
            Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.star_show);
            animation.setAnimationListener(listener);
            animation.setStartOffset(600);
            ImageView view = (ImageView) findViewById(R.id.image_star_01);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
        if (star >= 2) {
            Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.star_show);
            animation.setAnimationListener(listener);
            animation.setStartOffset(900);
            ImageView view = (ImageView) findViewById(R.id.image_star_02);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
        if (star >= 3) {
            Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.star_show);
            animation.setAnimationListener(listener);
            animation.setStartOffset(1200);
            ImageView view = (ImageView) findViewById(R.id.image_star_03);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
    }

    private void insertOrUpdateStar() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mParent);
        int level = Level.LEVEL_DATA.getLevel();
        int star = Level.LEVEL_DATA.getStar();
        int oldStar = databaseHelper.getLevelStar(level);

        if (oldStar == -1) {
            // If data doesn't exist, we add one
            databaseHelper.insertLevelStar(star);
        } else {
            // If data exist and new star is bigger, we update
            if (star > oldStar) {
                databaseHelper.updateLevelStar(level, star);
            }
        }

    }

    private static class StarAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Sounds.ADD_STAR.play();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }

}
