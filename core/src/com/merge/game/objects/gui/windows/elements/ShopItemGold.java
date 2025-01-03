package com.merge.game.objects.gui.windows.elements;

import com.merge.game.objects.DisplayObject;

public class ShopItemGold extends DisplayObject {

    private String _goldType;

    public ShopItemGold(DisplayObject parent, float scaleToW, String goldType) {

        _goldType = goldType;
        parent.addChild(this);
        setTexture();
    }
}
