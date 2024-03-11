package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.task.Task;

import java.util.ArrayList;

public class TaskArea extends DisplayObject {

    private ArrayList<Task> _tasksList = new ArrayList<>();

    public void init() {
        setSizeOfParent();
        setHeight(getHeight() * 0.9f);
        setY(this.parent.getHeight());

        initTasks();

    }

    private void initTasks() {
        for (int i = 0; i < Task.TASKS_COUNT; i++) {
            Task task = new Task();
            addChild(task);
            _tasksList.add(task);
            task.init(i);
        }
    }

    public void setTask(Task task, int indexPanel) {
        _taskItemsList.get(indexPanel).addTask(task);
        TaskItem2 taskItem = new TaskItem2();
        addChild(taskItem);
        taskItem.setSize(getWidth() / _countTask ,getHeight());
        taskItem.setX(indexPanel * taskItem.getWidth());
        taskItem.init(task.getTexture(), task.count);
    }
}
