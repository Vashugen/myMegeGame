package com.merge.game.objects.gui.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;

public class Button extends DisplayObject {

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

    private void setScale(float scale) {
        _scale = scale;
    }

    public void setDisabled(boolean state) {
        _isDisabled = state;
    }
}
