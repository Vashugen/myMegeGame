package com.merge.game.objects.game_elements.task;

import com.merge.game.logic.Globals;
import com.merge.game.logic.Tools;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.panels.TaskItem2;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class Task extends DisplayObject {

    public static final int TASKS_COUNT = 2;
    private ArrayList<TaskItem> addedTasks = new ArrayList<>();

/*    public int existsCount;
    public ArrayList<GridObject> itemsToRemove;*/

    private int _index;
    private Button _button;

    private ArrayList<TaskItem> taskItemsList;

    public Task(DisplayObject parent, int index) {
        parent.addChild(this);
        setTexture(TextureItems.taskField);
        scaleToFit(0.9f, 0.9f);
        setCenterCoeff(0.5f, 0.4f);
        _index = index;
        initButton();
        createTasks();
        //this.existsCount = 0;
        //this.itemsToRemove = new ArrayList<>();
    }

    private void initButton() {
        _button = createButton(TextureItems.taskDisabledButton, 0.10f, 0.5f, 1.3f);
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
                addedTasks.add(taskItem);
                setTask(taskItem, i, tasksCount);
            }else {
                i--;
            }
        }
    }

    private boolean taskExists(TaskItem taskItem) {
        for(int j = 0; j < addedTasks.size(); j++){
            if(addedTasks.get(j).equalsTask(taskItem)){
                return true;
            }
        }
        return false;
    }

    private void setTask(Task taskItem, int indexPanel, int tasksCount){

        float coeffFit = tasksCount == 1 ? 0.8f : 0.4f;

        taskItem.scaleToFit();
        taskItem.setSize(getWidth() / _countTask , getHeight());
        taskItem.setX(indexPanel * taskItem.getWidth());
        taskItem.init(task.getTexture(), task.count);

    }


}
