package com.merge.game.objects.game_elements.task;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.player_data.Player;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.Fonts;

import java.util.ArrayList;

public class TaskItem extends DisplayObject {

    private int _type, _level, _count;
    private String _generateType;
    private Label _label;

    public int _existsCount;
    public ArrayList<MergeItem> _itemsToRemove;

    public TaskItem(int type, int level, String generateType, int count) {
        setTexture(getTexture(type, level, generateType));
        _type = type;
        _level = level;
        _generateType = generateType;
        _count = count;
        _existsCount = 0;
        _itemsToRemove = new ArrayList<>();
    }

    public int getCount() {
        return _count;
    }

    public void initLabel() {
        _label = createLabel(Fonts.fontXSmall, "", 0.5f, 1.3f);
        updateLabel();
    }

    private void updateLabel() {
        _label.setText(_count + "");
        _label.setCenterCoeff(0.5f, 1.3f);

    }

    private static TextureRegion getTexture(int type, int level, String generateType) {
        TextureRegion[] textureRegions = GenerateItemType.getTexture(generateType, level);
        return textureRegions[type];
    }

    public boolean equalsTask(TaskItem taskItem) {
        return _type == taskItem._type && _level == taskItem._level && _generateType.equals(taskItem._generateType);
    }

    public boolean equalsTask(MergeItem mergeItem) {
        return _type == mergeItem.getType() && _level == mergeItem.getLevel() && _generateType.equals(mergeItem.getGenerateType());
    }
}