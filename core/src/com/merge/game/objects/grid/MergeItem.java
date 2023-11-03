package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.GameObjectType;
import com.merge.game.resources.textures.TextureItems;

public class MergeItem extends GridObject {

    public MergeItem(int type, int level, String generateType){
        super(getTexture(type, level, generateType));
        _type = type;
        _level = level;
        _generateType = generateType;
        _gameObjectType = GameObjectType.MERGE;
    }

    public MergeItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int type, int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, type, level, generateType);
        _gameObjectType = GameObjectType.MERGE;

    }

    private static TextureRegion getTexture(int type, int level, String generateType) {

        TextureRegion[] textureRegions = GenerateItemType.getTexture(generateType, level);
        return textureRegions[type];
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
        if(isFinalLevel()){
            _gameObjectType = GameObjectType.GENERATE;
            _generateType = GenerateItemType.getGenerateType(getGenerateType(), getType());
        }
    }
}
