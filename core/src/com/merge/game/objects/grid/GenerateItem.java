package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GenerateItem extends MergeItem {

    public GenerateItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, level, generateType);
    }
}
