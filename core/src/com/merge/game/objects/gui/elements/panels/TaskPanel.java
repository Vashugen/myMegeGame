package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.Task;
import com.merge.game.resources.textures.TextureItems;

public class TaskPanel extends DisplayObject {

    public void init(int countTask) {
        setSizeOfParent();
        setHeight(getParentHeight() / (countTask + 1));
        setY(getHeight() * countTask);

        createTask();
    }

    private void createTask() {

        Task task = new Task();
        addChild(task);
        task.init();

    }
}
