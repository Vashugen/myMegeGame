package com.merge.game.objects.gui.windows;

import com.merge.game.objects.gui.windows.elements.ShopItem;

public class WindowShop extends Window {

    public WindowShop(boolean canShowRewardVideo) {
        super(0.95f, 0.5f, 0.45f, true);
        initShopItems();

    }

    private void initShopItems() {
        int itemNum = 0;
        for (int i = 1; i <= 3; i++) {
            ShopItem item = new ShopItem(this, 0.18f, i);
            item.setCenterCoeff(0.27f + 0.23f * itemNum, 0.57f);
            itemNum++;
        }
    }
}
