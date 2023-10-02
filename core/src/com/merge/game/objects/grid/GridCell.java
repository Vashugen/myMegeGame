package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.resources.textures.TextureItems;

public class GridCell extends GridObject {

    public GridCell() {
        super(getRandomTexture());
        initCell();
    }

    private static TextureRegion getRandomTexture() {
        int index = (int) (Math.random() * TextureItems.gridCell.length);
        return TextureItems.gridCell[index];
    }

    private void initCell() {
        setSize(Globals.itemSize, Globals.itemSize);
    }
}
