package com.merge.game.objects.game_elements.task;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class Task extends DisplayObject {

    public static final int TASKS_COUNT = 2;

    public int existsCount;
    public ArrayList<GridObject> itemsToRemove;

    private ArrayList<TaskItem> taskItemsList;

    Button _button;

    public Task() {
        setTexture(TextureItems.taskField);
        initButton();
        //this.existsCount = 0;
        //this.itemsToRemove = new ArrayList<>();
    }

    private void initButton() {
        _button = new Button(TextureItems.taskDisabledButton);
        addChild(_button);
        //TODO расположить кнопку
    }

    public boolean exists(GridObject object) {
        return this.type == object.getType() && this.level == object.getLevel() && this.generateType.equals(object.getGenerateType());
    }

    public boolean exists(Task task) {
        return this.type == task.type && this.level == task.level && this.generateType.equals(task.generateType);
    }
}
