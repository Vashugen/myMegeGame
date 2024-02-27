package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;

import java.util.ArrayList;

public class LeftPanel extends DisplayObject {

    private static final int TASKS_COUNT = 2;

    private GoldEnergyPanel _goldEnergyArea;

    private ArrayList<TaskArea> tasks = new ArrayList<>();

    public ArrayList<TaskArea> getTasks() {
        return tasks;
    }

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY - Globals.offsetTop);
        setY(Globals.offsetTop);

        initGoldEnergyArea();
        initTasksArea();
    }

    private void initGoldEnergyArea() {
        _goldEnergyArea = new GoldEnergyPanel();
        addChild(_goldEnergyArea);
        _goldEnergyArea.init();
    }

    private void initTasksArea() {

        DisplayObject tasksArea = new DisplayObject();
        addChild(tasksArea);
        tasksArea.setSizeOfParent();
        tasksArea.setHeight(getHeight() * 0.9f);
        tasksArea.setY(_goldEnergyArea.getHeight());

        for (int i = 0; i < TASKS_COUNT; i++) {
            TaskArea taskArea = new TaskArea();
            tasksArea.addChild(taskArea);
            tasks.add(taskArea);
            taskArea.init(i);
        }
    }
}
