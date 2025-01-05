package com.merge.game.objects.gui.windows;

import com.merge.game.logic.api.InAppApi;
import com.merge.game.logic.player_data.Player;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.gui.windows.elements.ShopItemGold;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.Vocab;
import com.merge.game.resources.textures.TextureItems;

import java.util.Locale;

public class WindowShopGold extends Window {

    private Label _labelCoins;

    public WindowShopGold() {
        super(1.0f, 0.5f, 0.45f, true);
        initShopItems();
        setTitle(Vocab.textShop[Vocab.lang].toUpperCase());
        initPlayerCoins();
    }

    private void initShopItems(){

        for (int i = 0; i < 3; i++) {
            String inApp = "";
            switch (i){
                case 0:
                    inApp = InAppApi.ID_GOLD_1_DOLLAR;
                    break;
                case 1:
                    inApp = InAppApi.ID_GOLD_3_DOLLAR;
                    break;
                case 2:
                    inApp = InAppApi.ID_GOLD_5_DOLLAR;
                    break;
            }
            ShopItemGold item = new ShopItemGold(this,  0.18f, inApp);
            item.setCenterCoeff(0.27f + 0.23f * i, 0.57f);
        }

    }

    private void initPlayerCoins(){
        createObject(TextureItems.gold, 0.12f,  0.35f, 0.84f);

        DisplayObject frame = createObject(TextureItems.panelBonusCount, 0.32f,  0.59f,  0.84f);
        _labelCoins = frame.createLabel(Fonts.fontXXSmall, "", 0, 0);
        updateLabelCoins();
    }

    private void updateLabelCoins(){
        _labelCoins.setText("" + Player.get().getGold());
        _labelCoins.setCenterCoeff(0.5f, 0.45f);
    }
}
