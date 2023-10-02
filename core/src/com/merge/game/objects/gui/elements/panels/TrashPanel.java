package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.Trash;
import com.merge.game.resources.textures.TextureItems;

public class TrashPanel extends DisplayObject {

    Trash trash;

    public void init() {
        setSizeOfParent();
        setHeight(0.3f * getParentHeight());
        setY(getParentHeight() - getHeight());

        initTrash();
    }

    private void initTrash() {
        trash = new Trash(TextureItems.trash);
        addChild(trash);
        trash.init();
    }


}
