package com.merge.game.objects.gui.elements.panels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.textures.TextureItems;

public class TopPanel extends DisplayObject {

    private static final int PANELS_COUNT = 4;
    private PLPanel _scorePanel;
    private PLPanel _goldPanel;
    private PLPanel _levelPanel;
    private DisplayObject _clearPanel;

    private int _scoreCount;
    private int _goldCount;
    private int _levelCount;

    public TopPanel(int _scoreCount, int _goldCount, int _levelCount) {
        this._scoreCount = _scoreCount;
        this._goldCount = _goldCount;
        this._levelCount = _levelCount;
    }

    public PLPanel getScorePanel() {
        return _scorePanel;
    }

    public PLPanel getGoldPanel() {
        return _goldPanel;
    }

    public PLPanel getLevelPanel() {
        return _levelPanel;
    }

    public DisplayObject getClearPanel() {
        return _clearPanel;
    }

    public void init() {
        setSizeOfParent();
        setHeight(Globals.offsetY);

        _scorePanel = initPanel(TextureItems.score, _scoreCount, 0);
        _goldPanel = initPanel(TextureItems.gold, _goldCount, 1);
        _levelPanel = initPanel(TextureItems.level, _levelCount, 2);
        initClearPanel();
    }

    public void setScore(int score) {
        _scorePanel.getLabel().setString(score);
    }

    private PLPanel initPanel(TextureRegion texture, int count, int indexPanel) {
        PLPanel panel = new PLPanel();
        addChild(panel);
        panel.setSize(getWidth() / PANELS_COUNT ,getHeight());
        panel.setX(indexPanel * panel.getWidth());
        panel.init(texture, count);
        return panel;
    }

    private void initClearPanel() {
        _clearPanel = new DisplayObject();
        addChild(_clearPanel);
        _clearPanel.setSize(getWidth() / PANELS_COUNT ,getHeight());
        _clearPanel.setX(3 * _clearPanel.getWidth());
    }

    public void addClearButton(Button button) {
        _clearPanel.addChild(button);
        button.scaleToFit(0.8f, 0.8f);
        button.setCenterCoeff(0.5f, 0.5f);
    }
}
