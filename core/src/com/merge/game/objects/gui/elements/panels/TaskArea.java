package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.Task;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class TaskArea extends DisplayObject {

    TaskPanel taskPanel;
    Button taskButton;

    public void init(int count) {
        setSizeOfParent();
        setHeight(getParentHeight() / 2);
        setY(count * getHeight());
        initTaskPanel();
        initTaskButton();
    }

    private void initTaskPanel() {
        taskPanel = new TaskPanel(TextureItems.taskField);
        addChild(taskPanel);
        taskPanel.init();
    }

    private void initTaskButton() {
        taskButton = new Button(TextureItems.taskButton);
        addChild(taskButton);
        taskButton.scaleToFit(0.25f, 0.25f);
        taskButton.setCenterCoeff(0.5f, 0.5f);
        taskButton.setY(taskPanel.y + taskPanel.getHeight() + 0.05f); //TODO исправить на coeff
    }
}
