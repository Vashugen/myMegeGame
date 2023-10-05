package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;

public class LeftPanel extends DisplayObject {

    private static final int TASKS_COUNT = 1;

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY * 2);
        setY(Globals.offsetY);

        initTasksPanel();
    }

    private void initTasksPanel() {
        for (int i = 0; i < TASKS_COUNT; i++) {
            TaskPanel taskPanel = new TaskPanel();
            addChild(taskPanel);
            taskPanel.init(i);
        }
    }
}
