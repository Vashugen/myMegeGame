package com.merge.game.objects.gui.elements.panels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Tools;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.Task;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class TaskPanel extends DisplayObject {

    private static final int MAX_LEVEL_QUANTITY = 2;
    private static final float CENTER_COEFF_X = 0.125f;

    private ArrayList<Task> addedTasks = new ArrayList<>();

    private int _countTask;
    private int _countCoeff;

    public TaskPanel(TextureRegion texture) {
        super(texture);
    }

    public void init() {
        _countTask = Tools.randomInt(1, 3);
        _countCoeff = _countTask == 1 ? 2 : 1;
        scaleToFit(0.9f, 0.9f);
        setCenterCoeff(0.5f,0.4f);

        createTasks();
    }

    public ArrayList<Task> getAddedTasks() {
        return addedTasks;
    }

    private void createTasks() {
        int type, level, count;
        for (int i = 0; i < _countTask; i++){
            boolean createTask = true;
            String generateItemType = Globals.generateExists.get(Tools.randomInt(0, Globals.generateExists.size()));
            type = Tools.randomInt(1, TextureItems.kettle1.length);
            level = Tools.randomInt(0, MAX_LEVEL_QUANTITY) + 1;
            //level = Math.random() > 0.5 ? 2 : 3;
            count = Math.random() > 0.5 ? 1 : 2;
            Task task  = new Task(type, level, GenerateItemType.KETTLE, count);
            //проверка на дубли в заданиях, TODO check
            for(int j = 0; j < addedTasks.size(); j++){
                if(addedTasks.get(j).exists(task)){
                    createTask = false;
                    break;
                }
            }

            if(createTask){
                setTask(task, i, generateItemType);
                addedTasks.add(task);
            }
        }
    }

    private void setTask(Task task, int indexPanel, String generateItemType){

        DisplayObject taskObject = new MergeItem(task.type, task.level, generateItemType);

        TaskItem taskItem = new TaskItem();
        addChild(taskItem);
        taskItem.setSize(getWidth() / _countTask ,getHeight());
        taskItem.setX(indexPanel * taskItem.getWidth());
        taskItem.init(taskObject.getTexture(), task.count);

    }
}
