package com.merge.game.objects.gui.elements.buttons.bonus;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObjectType;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class BonusButton extends Button {

    private boolean _isActive;
    private int _bonusType;

    public BonusButton(int bonusType) {
        _bonusType = bonusType;
    }

    public BonusButton(DisplayObject parent, int bonusType) {
        parent.addChild(this);
        _bonusType = bonusType;
        setTexture(getTextureByType(bonusType));
        scaleToFit(0.7f, 0.7f);
        setCenterCoeff(0.5f, 0.5f);
    }

    public void init(int bonusType){
        setTexture(getTextureByType(bonusType));
        scaleToFit(0.7f, 0.7f);
        setCenterCoeff(0.5f, 0.5f);
    }

    private TextureRegion getTextureByType(int bonusType) {
        switch (bonusType) {
            case BonusType.FIX_GENERATE:
                return TextureItems.bonusFixGenerate;
            case BonusType.MAX_ITEM:
                return TextureItems.bonusMaxItem;
            case BonusType.RANDOM_ITEM:
                return TextureItems.bonusRandomItem;
        }

        return null;
    }

    public void setActive(boolean state) {
        _isActive = state;
    }

    @Override
    public void update() {
        //TODO заменить на подсветку
        if (_isActive) {
            setScale(getScale() + _scaleEffectSpeed * Globals.deltaTime);
            if (getScale() >= MAX_ACTIVE_SCALE && _scaleEffectSpeed > 0) {
                _scaleEffectSpeed = -SCALE_EFFECT_SPEED;
            }
            if (getScale() <= 1.0f && _scaleEffectSpeed < 0) {
                _scaleEffectSpeed = SCALE_EFFECT_SPEED;
            }
        } else {
            _scaleEffectSpeed = SCALE_EFFECT_SPEED;
            if (getScale() > 1.0f) {
                setScale(getScale() - _scaleEffectSpeed * Globals.deltaTime);
                if (getScale() < 1.0f) {
                    setScale(1.0f);
                }
            }
        }
    }

    public boolean getActiveState() {
        return _isActive;
    }

    public boolean isActive() {
        return _isActive == true;
    }

    public void activate(MergeItem item) {
        switch (_bonusType) {
            case BonusType.FIX_GENERATE:
                fixGenerate(item);
                break;
            case BonusType.MAX_ITEM:
                generateMaxLevelItems();
                break;
            case BonusType.RANDOM_ITEM:
                break;
        }
    }

    private void fixGenerate(MergeItem item) {
        if(item.getGameObjectType() != GameObjectType.GENERATE){
            return;
        }


    }
}
