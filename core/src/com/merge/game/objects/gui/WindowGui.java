package com.merge.game.objects.gui;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.windows.Window;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class WindowGui {

    private static final float FADE_ALPHA_INCREMENT = 0.04f;
    private static final float MAX_FADE_ALPHA = 0.5f;

    private static WindowGui _instance = null;

    public static WindowGui get(){
        return _instance == null ? new WindowGui() : _instance;
    }

    private DisplayObject _fade;
    private float _fadeAlpha = 0;
    private ArrayList<Window> _windowList;

    public WindowGui() {
        _windowList = new ArrayList<Window>();

        _fade = new DisplayObject(TextureItems.fade);
        _fade.setSize(Globals.screenWidth, Globals.screenHeight);
    }

    public void update() {
        updateFade();
        updateWindows();
    }

    private void updateFade() {
        if(_windowList.isEmpty()){
            if(_fadeAlpha > 0){
                _fadeAlpha -= FADE_ALPHA_INCREMENT * Globals.deltaTime;
            }
            if(_fadeAlpha < 0){
                _fadeAlpha = 0;
            }

        }else{
            if(_fadeAlpha < MAX_FADE_ALPHA){
                _fadeAlpha += MAX_FADE_ALPHA * Globals.deltaTime;
            }
        }

        _fade.setColor(0, 0, 0, _fadeAlpha);
    }

    private void updateWindows() {
        if(!_windowList.isEmpty()){
            _windowList.get(0).update();
        }
    }

    public  void addWindow(Window window){
        _windowList.add(0, window);
    }


}
