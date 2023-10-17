package com.merge.game.objects.game_elements;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class Task {

    public int type;
    public int level;
    public String generateType;
    public int count;

    public Task(int type, int level, String generateType, int count) {
        this.type = type;
        this.level = level;
        this.generateType = generateType;
        this.count = count;
    }

    public boolean exists(Task task) {
        return this.type == task.type && this.level == task.level && this.generateType.equals(task.generateType);
    }
}
