package com.merge.game.objects.game_elements;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class Task extends DisplayObject {

    private DisplayObject _taskField;
    private Button _taskButton;

    public void init() {
        initTaskField();
        initTaskButton();

    }

    private void initTaskField() {
        _taskField = new DisplayObject(TextureItems.taskField);
        scaleToFit(0.5f, 0.5f);
        setCenterCoeff(0.5f, 0.5f);
    }
}
