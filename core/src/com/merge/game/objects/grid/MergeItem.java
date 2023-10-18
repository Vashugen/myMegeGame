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

    public MergeItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int type,  int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, type, level, generateType);
    }

    private static TextureRegion getTexture(int type, int level, String generateType) {
        switch (generateType){
            case GenerateItemType.KETTLE:
                switch (level){
                    case 1:
                        return TextureItems.kettle1[type];
                    case 2:
                        return TextureItems.kettle2[type];
                }
        }

        return null;
    }

    public boolean match(GridObject gridObject) {
        return this.getLevel() == gridObject.getLevel() && this.getType() == gridObject.getType() && this.getGenerateType().equals(gridObject.getGenerateType());
    }

    public boolean isFinalLevel() {
        return MergeItemData.getFinalLevel(this) == this.getLevel();
    }

    public void updateLevel() {
        _level ++;
        setTexture(getTexture(this.getType(), this.getLevel(), this.getGenerateType()));
    }
}
