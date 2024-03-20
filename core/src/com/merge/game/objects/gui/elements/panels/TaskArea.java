package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.task.Task;

import java.util.ArrayList;

public class TaskArea extends DisplayObject {

    private ArrayList<Task> _tasksList = new ArrayList<>();

    public void init() {
        setSizeOfParent();
        setHeight(parent.getHeight() * 0.9f);
        setY(((LeftPanel) this.parent).getPanelInfo().getHeight());

        initTasks();
    }

    private void initTasks() {
        for (int i = 0; i < Task.TASKS_COUNT; i++) {
            Task task = new Task(this, i);
            _tasksList.add(task);
        }
    }

    public ArrayList getTasks() {
        return _tasksList;
    }
}
