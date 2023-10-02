package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;

public class LeftPanel extends DisplayObject {

    TaskPanel _taskOne;
    TaskPanel _taskTwo;

    public void init() {
        setSizeOfParent();
        setWidth(Globals.offsetX);

        initTasks();
    }

    private void initTasks() {
        
    }
}
