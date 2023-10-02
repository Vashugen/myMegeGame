package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;

public class RightPanel extends DisplayObject {

    TrashPanel _trashPanel;

    public void init() {
        setSizeOfParent();
        setWidth(Globals.offsetX);
        setX(Globals.screenWidth - Globals.offsetX);

        initUpgradePanel();
        initTrashPanel();
    }

    private void initUpgradePanel() {
    }

    private void initTrashPanel() {
        _trashPanel = new TrashPanel();
        addChild(_trashPanel);
        _trashPanel.init();
    }
}
