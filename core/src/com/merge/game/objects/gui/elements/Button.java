package com.merge.game.objects.gui.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.GameSound;

public class Button extends DisplayObject {

    private static final float SCALE_SPEED = 0.025f;
    private static final float SCALE_AFTER_PRESS = 0.9f;

    private Label _label = null;
    protected boolean _isDisabled = false;

    public Button() {
        super();
    }

    public Button(Texture texture) {
        super(texture);
    }

    public Button(TextureRegion texture){
        super(texture);
    }

    public boolean isPressed() {
        if(!_isDisabled && isJustTouched()){
            return true;
        }

        return false;
    }

    @Override
    public void update() {
        super.update();
        updateEffects();
        if (_isDisabled){
            return;
        }
        updateTouchEffect();
    }

    private void updateEffects() {
/*        if (!_IsDisabled && GetAlpha() == ALPHA_DISABLED)
            SetAlpha(1.0f);*/
        if (getScale() < 1.0f) {
            setScale(getScale() + SCALE_SPEED * Globals.deltaTime);
            if (getScale() > 1.0f)
                setScale(1.0f);
        }
    }

    private void updateTouchEffect() {
        if(isPressed()){
            //TODO проверка на выключенный звук
            GameSound.playSound(GameSound.button, GameSound.SOUND_VOLUME_BUTTON);
            setScale(SCALE_AFTER_PRESS);
        }
    }

    public void setLabel(BitmapFont font, String text) {
        setLabel(font, text, 0.5f, 0.45f);
    }

    public void setLabel(BitmapFont font, String text, float cx, float cy) {
        if(_label == null){
            _label = new Label(font, text);
            addChild(_label);
            _label.setCenterCoeff(cx, cy);
        }else {
            _label.setFont(font);
            _label.setText(text);
            _label.setCenterCoeff(cx, cy);
        }
    }

    public void setDisabled(boolean state){
        _isDisabled = state;
    }

    public void setDisabled(TextureRegion texture, boolean state) {
        setTexture(texture);
        setDisabled(state);
    }
}
