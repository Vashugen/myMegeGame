package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.resources.textures.TextureItems;

public class MergeItem extends GridObject {

    public MergeItem(int type, int level, String generateType){
        super(getTexture(type, level, generateType));
        _type = type;
        _level = level;
        _generateType = generateType;
    }

    private static TextureRegion getTexture(int type, int level, String generateType) {
        switch (generateType){
            case GenerateItemType.KETTLE:
                switch (type){
                    case 1:
                        return TextureItems.kettle1[level];
                    case 2:
                        return TextureItems.kettle2[level];
                }
        }

        return null;
    }

    public MergeItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, level, generateType);
    }
}
