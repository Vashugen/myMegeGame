package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.buttons.TaskButton;
import com.merge.game.resources.textures.TextureItems;

public class TaskArea extends DisplayObject {

    TaskPanel taskPanel;
    TaskButton taskButton;

    public void init(int count) {
        setSizeOfParent();
        setHeight(getParentHeight() / 2);
        setY(count * getHeight());
        generateTask();
    }

    public TaskPanel getTaskPanel() {
        return taskPanel;
    }

    public Button getTaskButton() {
        return taskButton;
    }

    public void generateTask(){
        initTaskPanel();
        initTaskButton();
    }

    public void update(){
        for (int i = 0; i < this.getTaskPanel().getAddedTasks().size(); i++) {
            this.getTaskPanel().getAddedTasks().get(i).existsCount = 0;
            this.getTaskPanel().getAddedTasks().get(i).itemsToRemove.clear();
        }
        inactiveButton();
    }

    private void initTaskPanel() {
        taskPanel = new TaskPanel(TextureItems.taskField);
        addChild(taskPanel);
        taskPanel.init();
    }

    private void initTaskButton() {
        taskButton = new TaskButton();
        addChild(taskButton);
        taskButton.init();
    }

    public void inactiveButton() {
        taskButton.setDisabled();
    }

    public void activeButton() {
        taskButton.setAbled();
    }

    public boolean buttonIsPressed() {
        return taskButton.isPressed();
    }

    public boolean tasksComplete() {
        //проверка количества имеющихся на поле итемсов
        for (int i = 0; i < taskPanel.getAddedTasks().size(); i++) {
            if(taskPanel.getAddedTasks().get(i).existsCount < taskPanel.getAddedTasks().get(i).count){
                return false;
            }
        }

        return true;
    }
}
