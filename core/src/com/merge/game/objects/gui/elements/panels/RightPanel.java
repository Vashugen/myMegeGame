package com.merge.game.objects.gui.elements.panels;

import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.game_elements.PanelBonus;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusButton;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusType;
import com.merge.game.resources.textures.TextureItems;

public class RightPanel extends DisplayObject {

    private PanelBonus _panelBonus;
    private TrashPanel _panelTrash;

    public void init() {

        setWidth(Globals.offsetX);
        setHeight(Globals.screenHeight - Globals.offsetY - Globals.offsetTop);
        setX(Globals.screenWidth - Globals.offsetX);
        setY(Globals.offsetTop);

        initBonusPanel();
        initPrizePanel();
        initTrashPanel();
    }

    public DisplayObject getTrashPanel() {
        return _panelTrash;
    }

    public PanelBonus getBonusPanel(){
        return _panelBonus;
    }

    private void initBonusPanel() {
        _panelBonus = new PanelBonus(this, TextureItems.bonusPanel, 0.7f, 0.8f);
    }

    private void initPrizePanel() {

    }

    private void initTrashPanel() {
        _panelTrash = new TrashPanel();
        addChild(_panelTrash);
        _panelTrash.init();
    }

    public void addBonus(BonusButton bonusButton, int i) {
        _panelBonus.addBonus(bonusButton, i);
    }
}
