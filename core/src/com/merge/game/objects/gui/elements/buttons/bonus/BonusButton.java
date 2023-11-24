package com.merge.game.objects.gui.elements.buttons.bonus;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class BonusButton extends Button {

    private int bonusType;

    public BonusButton(DisplayObject parent, int bonusType) {
        parent.addChild(this);
        setTexture(getTextureByType(bonusType));
        scaleToFit(0.3f, 0.3f);
        //initLabel();
    }

    private TextureRegion getTextureByType(int bonusType) {
        switch (bonusType){
            case BonusType.FIX_GENERATE:
                return TextureItems.bonusFixGenerate;
            case BonusType.MAX_ITEM:
                return TextureItems.bonusMaxItem;
            case BonusType.RANDOM_ITEM:
                return TextureItems.bonusRandomItem;
        }

        return null;
    }
}
