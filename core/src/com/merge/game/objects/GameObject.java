package com.merge.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameObject extends DisplayObject{

    protected int type;

    public GameObject(TextureRegion texture) {
        super(texture);
    }
}
