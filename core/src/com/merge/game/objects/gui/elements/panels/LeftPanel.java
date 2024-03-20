package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.task.Task;
import com.merge.game.objects.gui.elements.panels.info.InfoArea;

import java.util.ArrayList;

public class LeftPanel extends DisplayObject {

    public InfoArea _infoArea;
    private TaskArea taskArea;

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY - Globals.offsetTop);
        setY(Globals.offsetTop);

        initInfoArea();
        initTasksArea();
    }

    public InfoArea getPanelInfo() {
        return _infoArea;
    }

    private void initInfoArea() {
        _infoArea = new InfoArea();
        addChild(_infoArea);
        _infoArea.init();
    }

    private void initTasksArea() {
        taskArea = new TaskArea();
        addChild(taskArea);
        taskArea.init();
    }

    public void updateScore(int quantity) {
        _infoArea.updateScore(quantity);
    }

    public void updateGold(int quantity) {
        _infoArea.updateGold(quantity);
    }

    public ArrayList<Task> getTasks() {
        return taskArea.getTasks();
    }

}
