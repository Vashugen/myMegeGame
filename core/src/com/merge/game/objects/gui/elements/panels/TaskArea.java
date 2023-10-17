package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class TaskArea extends DisplayObject {

    public void init(int count) {
        setSizeOfParent();
        setHeight(getParentHeight() / 2);
        setY(count * getHeight());
        initTaskPanel();
        initTaskButton();
    }

    private void initTaskPanel() {
        TaskPanel taskPanel = new TaskPanel(TextureItems.taskField);
        addChild(taskPanel);
        taskPanel.init();
    }

    private void initTaskButton() {
        Button taskButton = new Button(TextureItems.taskButton);
        addChild(taskButton);
    }
}
