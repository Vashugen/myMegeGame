package com.merge.game.objects.gui.elements.buttons.bonus;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class BonusButton extends Button {

    private boolean _isActive;

    public BonusButton(DisplayObject parent, int bonusType) {
        parent.addChild(this);
        setTexture(getTextureByType(bonusType));
        scaleToFit(0.7f, 0.7f);
        setCenterCoeff(0.5f, 0.5f);
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

    public void setActive(boolean state) {
        _isActive = state;
    }

    @Override
    public void update() {
        //TODO заменить на подсветку
        if (_isActive) {
            setScale(getScale() + _scaleEffectSpeed * Globals.deltaTime);
            if (getScale() >= MAX_ACTIVE_SCALE && _scaleEffectSpeed > 0){
                _scaleEffectSpeed = -SCALE_EFFECT_SPEED;
            }
            if (getScale() <= 1.0f && _scaleEffectSpeed < 0){
                _scaleEffectSpeed = SCALE_EFFECT_SPEED;
            }
        } else {
            _scaleEffectSpeed = SCALE_EFFECT_SPEED;
            if (getScale() > 1.0f) {
                setScale(getScale() - _scaleEffectSpeed * Globals.deltaTime);
                if (getScale() < 1.0f){
                    setScale(1.0f);
                }
            }
        }
    }

    public boolean getActiveState() {
        return _isActive;
    }
}
