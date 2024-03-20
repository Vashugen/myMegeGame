package com.merge.game.objects.gui.elements.panels.info;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.gui.elements.panels.PLPanel;
import com.merge.game.resources.textures.TextureItems;

public class InfoArea extends DisplayObject {

    private static final int PANELS_COUNT = 2;

    private DisplayObject _panel;

    PLPanel _panelScore;
    PLPanel _panelGold;

    public void init(){
        setSizeOfParent();
        setHeight(parent.getHeight() * 0.1f);

        initField();
        initItems();
        //_panelScore = initPanel(TextureItems.gold, 0);
        //_panelGold = initPanel(TextureItems.score, 1);
    }

    private void initItems() {
        int itemNum = 0;
        for (int i = 0; i < 2; i++) {
            InfoItem item = new InfoItem(_panel, 0.2f, i);
            item.setCenterCoeff(0.16f + itemNum * 0.5f, 0.5f);
            itemNum ++;
        }
    }

    private void initField() {
        _panel = createObject(TextureItems.goldEnergyPanel,0.8f, 0.5f, 0.5f);
    }

    private PLPanel initPanel(TextureRegion texture, int indexPanel) {
        PLPanel panel = new PLPanel();
        addChild(panel);
        panel.setSize(getWidth() / PANELS_COUNT ,getHeight());
        panel.setX(indexPanel * panel.getWidth());
        panel.init(texture, 0);
        return panel;
    }

    public void updateScore(int quantity) {
        _panelScore.updateLabel(quantity);
    }

    public void updateGold(int quantity) {
        _panelGold.updateLabel(quantity);
    }
}
