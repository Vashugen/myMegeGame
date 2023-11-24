package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.resources.textures.TextureItems;

public class RightPanel extends DisplayObject {

    private DisplayObject _bonusPanel;
    private TrashPanel _trashPanel;

    public void init() {
        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY * 2);
        setX(Globals.screenWidth - Globals.offsetX);
        setY(Globals.offsetY);

        initBonusPanel();
        initTrashPanel();
    }

    public DisplayObject getTrashPanel() {
        return _trashPanel;
    }

    public DisplayObject getBonusPanel(){
        return _bonusPanel;
    }

    private void initBonusPanel() {
        _bonusPanel = new DisplayObject(TextureItems.bonusPanel);
        addChild(_bonusPanel);
        _bonusPanel.setSizeOfParent();
        _bonusPanel.scaleToFit(1.0f, 0.7f);
        _bonusPanel.setCenterCoeff(0.5f, 0);
        _bonusPanel.setY(0);

    }

    private void initTrashPanel() {
        _trashPanel = new TrashPanel();
        addChild(_trashPanel);
        _trashPanel.init();
    }
}
