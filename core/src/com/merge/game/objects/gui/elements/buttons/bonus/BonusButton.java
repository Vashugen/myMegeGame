package com.merge.game.objects.gui.elements.buttons.bonus;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.logic.player_data.Player;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.shader_effects.ShaderEffectObjectLightning;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.textures.TextureItems;

public class BonusButton extends Button {

    private boolean _isActive;
    private int _bonusType;
    private Label _labelCount;

    public BonusButton(int bonusType) {
        _bonusType = bonusType;
    }

    public void init(int bonusType){
        setTexture(getTextureByType(bonusType));
        scaleToFit(0.7f, 0.7f);
        setCenterCoeff(0.5f, 0.5f);
        addCount();
    }

    private TextureRegion getTextureByType(int bonusType) {
        switch (bonusType) {
            case BonusType.FIX_GENERATE:
                return TextureItems.bonusFixGenerate;
            case BonusType.MAX_ITEM:
                return TextureItems.bonusMaxItem;
            case BonusType.MAGIC_GENERATOR:
                return TextureItems.bonusRandomItem;
        }

        return null;
    }

    private void addCount() {
        DisplayObject bonusCountCircle = new DisplayObject(TextureItems.bonusCount);
        addChild(bonusCountCircle);
        bonusCountCircle.scaleToFit(0.5f, 0.5f);
        bonusCountCircle.setCenterCoeff(0.9f, 0.9f);
        _labelCount = new Label(Fonts.fontXSmall, Player.get().getBonusCount(_bonusType));
        bonusCountCircle.addChild(_labelCount);
        _labelCount.setCenterCoeff(0.5f, 0.4f);
    }

    public void setActive(boolean state) {
        _isActive = state;
        if (_isActive){
            setShaderEffect(new ShaderEffectObjectLightning(0.025f));
        }else {
            setShaderEffect(null);
        }
    }

    @Override
    public void update() {
        super.update();
        updateActivated();
        updateLabel();
    }

    private void updateActivated() {
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

    private void updateLabel() {
        _labelCount.setString(Player.get().getBonusCount(_bonusType));
        _labelCount.setCenterCoeff(0.5f, 0.4f);
    }

    public boolean getActiveState() {
        return _isActive;
    }

    public boolean isActive() {
        return _isActive == true;
    }

    public int getBonusType() {
        return _bonusType;
    }
}
