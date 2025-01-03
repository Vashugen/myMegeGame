package com.merge.game.objects.gui.windows;

import com.merge.game.logic.api.InAppApi;
import com.merge.game.objects.gui.elements.labels.Label;

public class WindowShopGold extends Window {

    private Label _labelCoins;

    public WindowShopGold() {
        super(1.0f, 0.5f, 0.45f, true);
        initShopItems();
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

        }

    }
}
