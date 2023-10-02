package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MergeItem extends GridObject {

    public MergeItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, level, generateType);
    }
}
