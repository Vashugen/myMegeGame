package com.merge.game.objects.game_elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.grid.GridObject;

import java.util.ArrayList;

public class Task extends DisplayObject {

    public int existsCount;
    public ArrayList<GridObject> itemsToRemove;

    public int type;
    public int level;
    public String generateType;
    public int count;

    public Task(int type, int level, String generateType, int count) {
        super(getTexture(type, level, generateType));
        this.type = type;
        this.level = level;
        this.generateType = generateType;
        this.count = count;
        this.existsCount = 0;
        this.itemsToRemove = new ArrayList<>();
    }

    private static TextureRegion getTexture(int type, int level, String generateType) {
        TextureRegion[] textureRegions = GenerateItemType.getTexture(generateType, level);
        return textureRegions[type];
    }

    public boolean exists(GridObject object) {
        return this.type == object.getType() && this.level == object.getLevel() && this.generateType.equals(object.getGenerateType());
    }

    public boolean exists(Task task) {
        return this.type == task.type && this.level == task.level && this.generateType.equals(task.generateType);
    }
}
