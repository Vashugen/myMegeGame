package com.merge.game.objects.gui.elements.panels.info;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.task.Task;
import com.merge.game.objects.gui.elements.panels.TaskArea;

import java.util.ArrayList;
import java.util.Map;

public class LeftPanel extends DisplayObject {

    public PanelInfo _panelInfo;

    private TaskArea taskArea;
    //private ArrayList<TaskItem> tasks = new ArrayList<>();

/*    public ArrayList<TaskItem> getTasks() {
        return tasks;
    }*/

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY - Globals.offsetTop);
        setY(Globals.offsetTop);

        initGoldEnergyArea();
        initTasksArea();
    }

    public PanelInfo getPanelInfo() {
        return _panelInfo;
    }

    private void initGoldEnergyArea() {
        _panelInfo = new PanelInfo();
        addChild(_panelInfo);
        _panelInfo.init();
    }

    private void initTasksArea() {
        taskArea = new TaskArea();
        addChild(taskArea);
        taskArea.init();
    }

    public void updateScore(int quantity) {
        _panelInfo.updateScore(quantity);
    }

    public void updateGold(int quantity) {
        _panelInfo.updateGold(quantity);
    }

    public ArrayList<Task> getTasks() {
        return taskArea.getTasks();
    }

}
