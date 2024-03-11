package com.merge.game.objects.gui.elements.panels.info;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.Task;
import com.merge.game.objects.gui.elements.panels.TaskArea;
import com.merge.game.objects.gui.elements.panels.TaskItem;

import java.util.ArrayList;

public class LeftPanel extends DisplayObject {

    public PanelInfo _panelInfo;

    private ArrayList<TaskItem> tasks = new ArrayList<>();

    public ArrayList<TaskItem> getTasks() {
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
        _panelInfo = new PanelInfo();
        addChild(_panelInfo);
        _panelInfo.init();
    }

    private void initTasksArea() {

        TaskArea tasksArea = new TaskArea();
        addChild(tasksArea);
        tasksArea.init();
    }

    public void updateScore(int quantity) {
        _panelInfo.updateScore(quantity);
    }

    public void updateGold(int quantity) {
        _panelInfo.updateGold(quantity);
    }

    public void setTask(Task task, int indexPanel) {

    }
}
