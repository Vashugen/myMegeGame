package com.merge.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;

public class Background extends DisplayObject {

    public Background(Texture texture) {
        super(texture);
        initBackground();
    }

    public Background(TextureRegion texture) {
        super(texture);
        initBackground();
    }

    private void initBackground(){
        scaleToWidth(1.0f);
        if(getHeight() < Globals.screenHeight){
            scaleToHeight(1.0f);
        }
        setCenterCoeff(0.5f, 0.5f);
    }
}
