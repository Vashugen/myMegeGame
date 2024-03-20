package com.merge.game.objects.game_elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusButton;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusType;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class PanelBonus extends DisplayObject {

    ArrayList<DisplayObject> _squares = new ArrayList<>();

    public PanelBonus(DisplayObject parent, TextureRegion textureRegion, float coeffW, float coeffH) {
        setTexture(textureRegion);
        parent.addChild(this);
        setWidth(parent.width * 0.35f);
        setHeight(parent.height * 0.7f);
        //scaleToFit(coeffW, coeffH);
        //setHeight(coeffH);
        setCenterCoeff(0.25f, 0.5f);
        setY(0);
        initBonusSquare();
        //initBonusButtons();
    }

    private void initBonusSquare() {
        float maxBonusSquareHeight = getHeight() / BonusType.BONUS_QUANTITY;
        float bonusSquareSize = getWidth() < maxBonusSquareHeight ? getWidth() : maxBonusSquareHeight;
        for (int i = 0; i < BonusType.BONUS_QUANTITY; i++) {
            DisplayObject bonusSquare = new DisplayObject(TextureItems.bonusSquare);
            _squares.add(bonusSquare);
            addChild(bonusSquare);
            bonusSquare.scaleToSquareSize(bonusSquareSize);
            bonusSquare.setCenterCoeffX(0.5f);
            bonusSquare.setY(bonusSquareSize * i);
        }
    }

    public void addBonus(BonusButton bonusButton, int i) {
        _squares.get(i-1).addChild(bonusButton);
        bonusButton.init(i);
    }
}
