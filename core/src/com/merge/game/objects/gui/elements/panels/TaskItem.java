package com.merge.game.objects.gui.elements.panels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.task.Task;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class TaskItem extends DisplayObject {

    private int _count, _type, _level, _tasksCount;
    private String _generatorType;
    
    public TaskItem (int type, int level, String generatorType, int count, int tasksCount) {
        _type = type;
        _level = level;
        _generatorType = generatorType;
        _count = count;
        _tasksCount = tasksCount;
        setTexture(getTexture(_type, _level, generatorType));
    }

    private static TextureRegion getTexture(int type, int level, String generatorType) {
        TextureRegion[] textureRegions = GenerateItemType.getTexture(generatorType, level);
        return textureRegions[type];
    }

    public DisplayObject getTaskPanel() {
        return taskPanel;
    }

    public Button getTaskButton() {
        return taskButton;
    }

    public void generateTaskPanel(){
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
        taskPanel = new DisplayObject(TextureItems.taskField);
        addChild(taskPanel);
        _countTask = Tools.randomInt(1, 3);
        _countCoeff = _countTask == 1 ? 2 : 1;
        scaleToFit(0.9f, 0.9f);
        setCenterCoeff(0.5f,0.4f);
    }

    private void initTaskButton() {
        taskButton = new Button(TextureItems.taskDisabledButton);
        addChild(taskButton);
        scaleToFit(0.25f, 0.25f);
        setCenterCoeff(0.5f, 0.5f);
        setY(((TaskItem) this.parent).getTaskPanel().y + ((TaskItem) this.parent).getTaskPanel().getHeight() + 0.05f); //TODO исправить на coeff
        taskButton.setDisabled(true);
    }

    public void inactiveButton() {
        taskButton.setDisabled(true);
    }

    public void activeButton() {
        taskButton.setDisabled(false);
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
