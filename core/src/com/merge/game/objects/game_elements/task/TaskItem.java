package com.merge.game.objects.game_elements.task;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GenerateItemType;

import java.util.ArrayList;

public class TaskItem extends DisplayObject {

    private int _type;
    private int _level;
    private String _generateType;
    private int _count;

    public TaskItem(int type, int level, String generateType, int count) {
        texture = getTexture(type, level, generateType);
        _type = type;
        _level = level;
        _generateType = generateType;
        _count = count;
        //this.existsCount = 0;
        //this.itemsToRemove = new ArrayList<>();
    }

    private static TextureRegion getTexture(int type, int level, String generateType) {
        TextureRegion[] textureRegions = GenerateItemType.getTexture(generateType, level);
        return textureRegions[type];
    }
}
