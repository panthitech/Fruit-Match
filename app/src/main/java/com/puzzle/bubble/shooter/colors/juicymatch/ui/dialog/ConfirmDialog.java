package com.puzzle.bubble.shooter.colors.juicymatch.ui.dialog;

import android.view.View;

import com.puzzle.bubble.shooter.colors.juicymatch.R;
import com.puzzle.bubble.shooter.colors.juicymatch.asset.Sounds;
import com.puzzle.bubble.shooter.colors.juicymatch.database.DatabaseHelper;
import com.puzzle.bubble.shooter.colors.juicymatch.item.Item;
import com.puzzle.bubble.shooter.colors.juicymatch.item.product.Product;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;


public class ConfirmDialog extends BaseDialog implements View.OnClickListener {

    private final Product mProduct;

    private int mSelectedId;

    public ConfirmDialog(GameActivity activity, Product product) {
        super(activity);
        mProduct = product;
        setContentView(R.layout.dialog_confirm);
        setContainerView(R.layout.dialog_container);
        setEnterAnimationId(R.anim.enter_from_center);
        setExitAnimationId(R.anim.exit_to_center);

        // Init product image
        GameImage imageProduct = (GameImage) findViewById(R.id.image_product);
        imageProduct.popUp(200, 300);
        imageProduct.setImageResource(mProduct.getDrawableId());

        // Init text
        GameText txtConfirm = (GameText) findViewById(R.id.txt_confirm);
        txtConfirm.popUp(200, 500);

        // Init button
        GameButton btnConfirm = (GameButton) findViewById(R.id.btn_confirm);
        btnConfirm.popUp(200, 700);
        btnConfirm.setOnClickListener(this);

        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_watch_ad) {
            showMoreCoinDialog();
        } else if (mSelectedId == R.id.btn_confirm) {
            showNewBoosterDialog();
            updateCoin();
        }
    }

    @Override
    public void onClick(View view) {
        Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismiss();
        } else if (id == R.id.btn_confirm) {
            mSelectedId = id;
            buyProduct();
            dismiss();
        }
    }
    private void buyProduct() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mParent);
        // Check is player has enough saving coin
        int price = mProduct.getPrice();
        int saving = databaseHelper.getItemCount(Item.COIN);
        if (saving < price) {
            // Show the ad dialog if saving not enough
            mSelectedId = R.id.btn_watch_ad;
            return;
        }

        // Update coin from db
        databaseHelper.updateItemCount(Item.COIN, saving - price);

        // Update item from db
        int num = databaseHelper.getItemCount(mProduct.getName());
        databaseHelper.updateItemCount(mProduct.getName(), num + 1);
    }

    private void showMoreCoinDialog() {
        MoreCoinDialog moreCoinDialog = new MoreCoinDialog(mParent);
        mParent.showDialog(moreCoinDialog);
    }

    private void showNewBoosterDialog() {
        NewBoosterDialog newBoosterDialog = new NewBoosterDialog(mParent, mProduct);
        mParent.showDialog(newBoosterDialog);
    }

    public void updateCoin() {
    }

}
