package com.merge.game.objects.gui.windows.elements;

import com.merge.game.logic.api.InAppApi;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.Vocab;
import com.merge.game.resources.textures.TextureItems;

public class ShopItemGold extends DisplayObject {

    private String _goldType;
    private Button _buttonBuy;

    public ShopItemGold(DisplayObject parent, float scaleToW, String goldType) {

        _goldType = goldType;
        parent.addChild(this);
        setTexture(InAppApi.getInAppTexture(_goldType));
        scaleToWidth(scaleToW);

        initButtonBuy();
        initLabelCount();
    }

    private void initButtonBuy(){
        _buttonBuy = createButton(TextureItems.buttonBuy, 1.05f, 0.5f, 1.3f);
        _buttonBuy.setLabel(Fonts.fontXXSmall, InAppApi.GetInAppCost(_goldType));
    }

    private void initLabelCount(){
        DisplayObject frame = createObject(TextureItems.panelBonusCount, 0.98f, 0.5f, -0.3f);
        frame.createLabel(Fonts.fontXXSmall, InAppApi.getInAppReward(_goldType) + "", 0.5f,  0.5f);
        frame.createLabel(Fonts.fontXXSmall, Vocab.textGold[Vocab.lang], 0.5f, -0.35f);
    }


    @Override
    public void update() {
        super.update();
        updateButtonBuy();
    }

    private void updateButtonBuy(){
        if(_buttonBuy.isPressed()){
            //купить
        }
    }
}
