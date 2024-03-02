package com.merge.game.objects.gui.windows;

import com.merge.game.objects.DisplayObject;
import com.merge.game.resources.textures.TextureItems;

public class Window extends DisplayObject {

    public Window(){
        setTexture(TextureItems.window);
        scaleToWidth(0.9f);
        setCenterCoeff(0.5f, 0.45f);
        addButtonCloseDefault();
    }

    public Window(float scaleToWidth, float cx, float cy, boolean buttonClose){

    }


    private void addButtonCloseDefault() {
        addButtonClose();
    }

    private void addButtonClose(){

    }

}
