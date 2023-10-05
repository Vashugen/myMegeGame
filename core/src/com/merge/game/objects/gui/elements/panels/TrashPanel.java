package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;

public class TrashPanel extends DisplayObject {

    public void init() {
        setSizeOfParent();
        setHeight(0.3f * getParentHeight());
        setY(getParentHeight() - getHeight());
    }


}
