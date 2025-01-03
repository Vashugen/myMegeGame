package com.merge.game.objects.gui.windows;

import com.merge.game.logic.player_data.Player;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.gui.windows.elements.ShopItem;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.textures.TextureItems;

public class WindowShop extends Window {

    private Label _labelCoins;
    private Button _buttonAddGold;

    public WindowShop(boolean canShowRewardVideo) {
        super(0.95f, 0.5f, 0.45f, true);
        initShopItems();
        initPlayerCoins();
    }

    @Override
    protected void updateWindow() {
        if(_buttonClose.isPressed()){
            disappear();
        }

        updateLabelCoins();

        if(_buttonAddGold.isPressed()){

        }
    }

    private void initShopItems() {
        int itemNum = 0;
        for (int i = 1; i <= 3; i++) {
            ShopItem item = new ShopItem(this, 0.18f, i);
            item.setCenterCoeff(0.27f + 0.23f * itemNum, 0.57f);
            itemNum++;
        }
    }

    private void initPlayerCoins(){

        createObject(TextureItems.gold, 0.12f, 0.35f, 0.84f);

        DisplayObject frame = createObject(TextureItems.panelPlayerGold, 0.32f, 0.59f, 0.84f);
        _labelCoins = frame.createLabel(Fonts.fontXXSmall, "", 0, 0);
        updateLabelCoins();
    }

    private void updateLabelCoins(){
        _labelCoins.setText("" + Player.get().getGold());
        _labelCoins.setCenterCoeff(0.5f, 0.45f);
    }
}
