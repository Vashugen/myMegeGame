package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;

public class RightPanel extends DisplayObject {

    private TrashPanel _trashPanel;

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY * 2);
        setX(Globals.screenWidth - Globals.offsetX);
        setY(Globals.offsetY);

        initUpgradePanel();
        initTrashPanel();
    }

    public DisplayObject getTrashPanel() {
        return _trashPanel;
    }

    private void initUpgradePanel() {
    }

    private void initTrashPanel() {
        _trashPanel = new TrashPanel();
        addChild(_trashPanel);
        _trashPanel.init();
    }


}
