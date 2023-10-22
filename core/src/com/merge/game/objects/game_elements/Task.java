package com.merge.game.objects.game_elements;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObject;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class Task {

    public int existsCount;
    public ArrayList<GridObject> itemsToRemove;

    public int type;
    public int level;
    public String generateType;
    public int count;

    public Task(int type, int level, String generateType, int count) {
        this.type = type;
        this.level = level;
        this.generateType = generateType;
        this.count = count;
        this.existsCount = 0;
        this.itemsToRemove = new ArrayList<>();
    }

    public boolean exists(GridObject object) {
        return this.type == object.getType() && this.level == object.getLevel() && this.generateType.equals(object.getGenerateType());
    }

    public boolean exists(Task task) {
        return this.type == task.type && this.level == task.level && this.generateType.equals(task.generateType);
    }
}
