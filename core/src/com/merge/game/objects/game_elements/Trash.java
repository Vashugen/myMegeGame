package com.merge.game.objects.game_elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.GameObjectType;
import com.merge.game.objects.grid.GridObject;

public class Trash extends GridObject {

    public Trash(TextureRegion texture) {
        super(texture);
        _gameObjectType = GameObjectType.TRASH;
    }

    public void init() {
        scaleToFit(0.9f, 0.9f);
        setCenterCoeff(0.5f, 0.5f);
    }
}
