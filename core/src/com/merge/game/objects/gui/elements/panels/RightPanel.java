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
        setHeight(Globals.screenHeight - Globals.offsetY * 2);
        setX(Globals.screenWidth - Globals.offsetX);
        setY(Globals.offsetY);

        initBonusPanel();
        initTrashPanel();
    }

    public DisplayObject getTrashPanel() {
        return _panelTrash;
    }

    public PanelBonus getBonusPanel(){
        return _panelBonus;
    }

    private void initBonusPanel() {
        _panelBonus = new PanelBonus(this, TextureItems.bonusPanel, 1.0f, 0.7f);
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
