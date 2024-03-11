package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.task.Task;

import java.util.ArrayList;

public class TaskArea extends DisplayObject {

    private ArrayList<Task> _tasksList = new ArrayList<>();

    public void init() {
        setSizeOfParent();
        setHeight(parent.getHeight() * 0.9f);
        setY(parent.getHeight());

        initTasks();
    }

    private void initTasks() {
        for (int i = 0; i < Task.TASKS_COUNT; i++) {
            Task task = new Task(this, i);
            _tasksList.add(task);
        }
    }

    public void setTask(TaskItem task, int index, int tasksCount) {

    }

/*    public void setTask(Task task, int indexPanel) {
        _tasksList.get(indexPanel).addTask(task);
        TaskItem2 taskItem = new TaskItem2();
        addChild(taskItem);
        taskItem.setSize(getWidth() / _countTask ,getHeight());
        taskItem.setX(indexPanel * taskItem.getWidth());
        taskItem.init(task.getTexture(), task.count);
    }*/
}
