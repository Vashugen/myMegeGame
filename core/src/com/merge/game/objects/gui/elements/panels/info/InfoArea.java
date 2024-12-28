package com.merge.game.objects.gui.elements.panels.info;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.player_data.Player;
import com.merge.game.logic.player_data.PlayerItem;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.gui.elements.panels.PLPanel;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class InfoArea extends DisplayObject {

    private static final int PANELS_COUNT = 2;
    private DisplayObject _panel;
    private ArrayList<InfoItem> _infoItems = new ArrayList<>();

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
            _infoItems.add(i, item);
            itemNum ++;
        }
    }

    private void initField() {
        _panel = createObject(TextureItems.goldEnergyPanel,0.8f, 0.5f, 0.5f);
    }

    public void updateGold() {
        InfoItem item = _infoItems.get(PlayerItem.GOLD);
        item.updateLabel();
    }

    public void updateScore() {
        InfoItem item = _infoItems.get(PlayerItem.SCORE);
        item.updateLabel();
    }
}
