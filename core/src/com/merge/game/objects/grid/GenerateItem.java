package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.GameObjectType;

public class GenerateItem extends MergeItem {

    public GenerateItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int type, int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, type, level, generateType);
        _gameObjectType = GameObjectType.GENERATE;
    }
}
