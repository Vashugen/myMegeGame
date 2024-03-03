package com.merge.game.objects.gui.windows;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.resources.textures.TextureItems;

public class Window extends DisplayObject {

    private static final int STATE_APPEARING = 0;
    private static final int STATE_UPDATE = 1;
    private static final int STATE_DISAPPEARING = 2;
    private static final float ALPHA_SPEED = 0.04f;
    private int _state = STATE_APPEARING;

    public Window(){
        setTexture(TextureItems.window);
        scaleToWidth(0.9f);
        setCenterCoeff(0.5f, 0.45f);
        addButtonCloseDefault();
    }

    public Window(float scaleToWidth, float cx, float cy, boolean buttonClose){

    }

    @Override
    public void update() {
        super.update();
        switch (_state){
            case STATE_APPEARING:
                updateAppearing();
                break;
            case STATE_UPDATE:
                updateWindow();
                break;
            case STATE_DISAPPEARING:
                updateDisappearing();
                break;
        }
    }

    private void updateAppearing() {
        if(getOwnAlpha() < 1){
            setAlpha(getOwnAlpha() + ALPHA_SPEED * Globals.deltaTime);
            if (getOwnAlpha() == 1){
                _state = STATE_UPDATE;
            }
        }else {
            _state = STATE_UPDATE;
        }
    }

    private void updateWindow() {
    }

    private void updateDisappearing() {
    }

    private void addButtonCloseDefault() {
        addButtonClose();
    }

    private void addButtonClose(){

    }

}
