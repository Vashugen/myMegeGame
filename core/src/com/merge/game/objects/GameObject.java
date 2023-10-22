package com.merge.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameObject extends DisplayObject{

    protected int _gameObjectType;

    public GameObject(TextureRegion texture) {
        super(texture);
    }

    public int getGameObjectType() {
        return _gameObjectType;
    }

    public boolean canBeMerge() {
        return _gameObjectType == GameObjectType.MERGE;
    }
}
