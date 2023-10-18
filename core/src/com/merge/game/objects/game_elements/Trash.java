package com.merge.game.objects.game_elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObject;
import com.merge.game.objects.GameObjectType;
import com.merge.game.resources.textures.TextureItems;

public class Trash extends GameObject {

    public Trash(TextureRegion texture) {
        super(texture);
        _gameObjectType = GameObjectType.TRASH;
    }

    public void init() {
        scaleToFit(0.5f, 0.5f);
        setCenterCoeff(0.5f, 0.5f);
    }
}
