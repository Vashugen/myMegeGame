package com.merge.game.objects.game_elements.task;

import com.merge.game.logic.Globals;
import com.merge.game.logic.Tools;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class Task extends DisplayObject {

    public static final int TASKS_COUNT = 2;
    private ArrayList<TaskItem> addedTaskItemsList = new ArrayList<>();

/*    public int existsCount;
    public ArrayList<GridObject> itemsToRemove;*/

    private int _index;
    private Button _button;

    private ArrayList<TaskItem> taskItemsList;

    public Task(DisplayObject parent, int index) {
        parent.addChild(this);
        _index = index;
        setTexture(TextureItems.taskField);
        scaleToWidth(0.8f);
        setCenterCoeff(0.5f, 0.25f + index * 0.5f);

        initButton();
        createTasks();
        //this.existsCount = 0;
        //this.itemsToRemove = new ArrayList<>();
    }

    private void initButton() {
        _button = createButton(TextureItems.taskDisabledButton, 0.4f, 0.5f, 1.05f);
    }

    private void createTasks() {
        int type, level, count;
        int tasksCount = Tools.randomInt(1, 3);
        for (int i = 0; i < tasksCount; i++){
            String generateItemType = Globals.generateExists.get(Tools.randomInt(0, Globals.generateExists.size()));
            type = Tools.randomInt(1, GenerateItemType.getTexture(generateItemType, 1).length);
            level = Tools.randomInt(1, 3); //TODO потом изменить на ДО максимального уже появлявшегося в игре + ОТ поднимать в зависимости от уровня            count = Math.random() > 0.5 ? 1 : 2;
            count = Tools.randomInt(1, 3);
            TaskItem taskItem  = new TaskItem(type, level, generateItemType, count);
            //проверка на дубли в заданиях, TODO check
            if(!taskExists(taskItem)){
                addChild(taskItem);
                addedTaskItemsList.add(taskItem);
                setTask(taskItem, i, tasksCount);
            }else {
                i--;
            }
        }
    }

    private boolean taskExists(TaskItem taskItem) {
        for(int j = 0; j < addedTaskItemsList.size(); j++){
            if(addedTaskItemsList.get(j).equalsTask(taskItem)){
                return true;
            }
        }
        return false;
    }

    private void setTask(TaskItem taskItem, int indexPanel, int tasksCount){

        float coeffFit = tasksCount == 1 ? 0.7f : 0.4f;
        float coeffCenter = tasksCount == 1 ? 2 : 1;

        taskItem.scaleToFit(coeffFit, coeffFit);
        taskItem.setCenterCoeff(0.25f * coeffCenter + 0.5f * indexPanel, 0.35f);
        //taskItem.setX(indexPanel * taskItem.getWidth());
        taskItem.initLabel();

    }


}
