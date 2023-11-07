package com.merge.game.objects.gui.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.resources.GameSound;

public class Button extends DisplayObject {

    private static final float SCALE_SPEED = 0.025f;
    private static final float SCALE_AFTER_PRESS = 0.9f;
    
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

    public void setDisabled(boolean state) {
        _isDisabled = state;
    }

    @Override
    public void update() {
        super.update();
        updateEffects();
        if (_isDisabled)
            return;
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
}
