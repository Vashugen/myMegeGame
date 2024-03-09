package com.merge.game.objects.gui.windows.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.player_data.Player;
import com.merge.game.logic.player_data.PlayerItem;
import com.merge.game.logic.player_data.PreferencesParams;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusType;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.gui.windows.WindowShop;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.textures.TextureItems;

public class ShopItem extends DisplayObject {

    private int _bonusType;
    private Button _buttonBuy;
    private Label _labelCount;

    public ShopItem(DisplayObject parent, float scaleToWidth, int bonusType) {
        _bonusType = bonusType;

        parent.addChild(this);
        setTexture(BonusType.getItemTexture(bonusType));
        scaleToWidth(scaleToWidth);

        initButtonBuy();
        initLabelCount();
    }

    private void initButtonBuy() {
        _buttonBuy = createButton(TextureItems.buttonBuy, 1.05f, 0.5f, 1.3f);
        _buttonBuy.setLabel(Fonts.fontMedium, "" + BonusType.getItemCost(_bonusType));

    }

    private void initLabelCount() {
        DisplayObject frame = createObject(TextureItems.panelBonusCount, 0.98f, 0.5f, -0.3f);
        _labelCount = frame.createLabel(Fonts.fontMedium, "", 0, 0);
        updateLabel();

    }

    private void updateLabel() {
        _labelCount.setText(Player.get().getBonusCount(_bonusType) + "");
        _labelCount.setCenterCoeff(0.5f, 0.5f);

    }
}
