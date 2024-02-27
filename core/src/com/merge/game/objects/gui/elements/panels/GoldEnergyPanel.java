package com.merge.game.objects.gui.elements.panels;

import com.merge.game.objects.DisplayObject;

public class GoldEnergyPanel extends DisplayObject {

    DisplayObject _panelEnergy;
    DisplayObject _panelGold;

    public void init(){
        setSizeOfParent();
        setHeight(parent.getHeight() * 0.1f);

        initPanelEnergy();
        initPanelGold();
    }

    private void initPanelEnergy() {
        _panelEnergy = new DisplayObject();
        _panelEnergy.setSizeOfParent();
        _panelEnergy.setWidth(parent.getWidth() * 0.5f);
    }

    private void initPanelGold() {
        _panelGold = new DisplayObject();
        _panelGold.setSizeOfParent();
        _panelGold.setWidth(parent.getWidth() * 0.5f);
        _panelGold.setX(_panelEnergy.getWidth());
    }
}
