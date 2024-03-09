package com.merge.game.objects.gui.windows;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.textures.TextureItems;

public class Window extends DisplayObject {

    private static final int STATE_APPEARING = 0;
    private static final int STATE_UPDATE = 1;
    private static final int STATE_DISAPPEARING = 2;
    private static final float ALPHA_SPEED = 0.04f;
    private static final float ALPHA_SPEED_DISAPPEAR = 0.04f;
    private int _state = STATE_APPEARING;

    protected Button _buttonClose;

    public Window(){
        setTexture(TextureItems.windowShop);
        scaleToFit(0.8f, 0.8f);
        setCenterCoeff(0.5f, 0.45f);
        addButtonCloseDefault();
        setAlpha(0);
    }

    public Window(float scaleToWidth, float cx, float cy, boolean buttonClose){
        setTexture(TextureItems.windowShop);
        scaleToWidth(scaleToWidth);
        setCenterCoeff(cx, cy);
        if(buttonClose){
            addButtonCloseDefault();
        }

        setAlpha(0);
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
        if(_buttonClose != null && _buttonClose.isPressed()){
            disappear();
        }
    }

    private void disappear() {
        _state = STATE_DISAPPEARING;
    }

    private void updateDisappearing() {
        if(getOwnAlpha() > 0){
            setAlpha(getOwnAlpha() - ALPHA_SPEED_DISAPPEAR * Globals.deltaTime);
            if(getOwnAlpha() <= 0){
                setToDelete();
            }
        }
    }

    private void addButtonCloseDefault() {
        addButtonClose(0.15f, 0.86f, 0.32f);
    }

    private void addButtonClose(float scaleToWidth, float cx, float cy) {
        _buttonClose = new Button(TextureItems.buttonClose);
        addChild(_buttonClose);
        _buttonClose.scaleToWidth(scaleToWidth);
        _buttonClose.setCenterCoeff(cx, cy);
    }

    protected void setTitle(String title){
        createLabel(Fonts.fontLarge, title, 0.5f, 0.31f);
    }

}
