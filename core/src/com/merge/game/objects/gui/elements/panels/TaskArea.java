package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.Task;

public class TaskArea extends DisplayObject {

    public void init() {
        setSizeOfParent();
        setHeight(getHeight() * 0.9f);
        setY(this.parent.getHeight());

        initTaskItems();

    }

    private void initTaskItems() {
        for (int i = 0; i < Task.TASKS_COUNT; i++) {
            TaskItem taskItem = new TaskItem();
            addChild(taskItem);
            //tasks.add(taskItem);
            taskItem.init(i);
        }
    }

}
