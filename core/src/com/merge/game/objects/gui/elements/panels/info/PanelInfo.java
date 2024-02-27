package com.merge.game.objects.gui.elements.panels.info;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.panels.PLPanel;
import com.merge.game.resources.textures.TextureItems;

public class PanelInfo extends DisplayObject {

    private static final int PANELS_COUNT = 2;

    PLPanel _panelScore;
    PLPanel _panelGold;

    public void init(){
        setSizeOfParent();
        setHeight(parent.getHeight() * 0.1f);

        _panelScore = initPanel(TextureItems.gold, 0);
        _panelGold = initPanel(TextureItems.score, 1);
    }

    private PLPanel initPanel(TextureRegion texture, int indexPanel) {
        PLPanel panel = new PLPanel();
        addChild(panel);
        panel.setSize(getWidth() / PANELS_COUNT ,getHeight());
        panel.setX(indexPanel * panel.getWidth());
        panel.init(texture, 0);
        return panel;
    }
}
