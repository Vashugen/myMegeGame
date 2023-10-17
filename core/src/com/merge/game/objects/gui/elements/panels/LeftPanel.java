package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;

public class LeftPanel extends DisplayObject {

    private static final int TASKS_COUNT = 2;

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY * 2);
        setY(Globals.offsetY);

        initTasksArea();
    }

    private void initTasksArea() {
        for (int i = 0; i < TASKS_COUNT; i++) {
            TaskArea taskArea = new TaskArea();
            addChild(taskArea);
            taskArea.init(i);
        }
    }
}
