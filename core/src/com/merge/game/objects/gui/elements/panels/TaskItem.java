package com.merge.game.objects.gui.elements.panels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.Fonts;

public class TaskItem extends DisplayObject {

    private DisplayObject _picture;
    private Label _label;

    public void init(TextureRegion texture, int countTask) {
        initPicture(texture);
        initLabel(countTask);
    }

    private void initPicture(TextureRegion texture) {
        _picture = new DisplayObject(texture);
        addChild(_picture);
        _picture.scaleToFit(0.7f, 0.7f);
        _picture.setCenterCoeff(0.5f, 0.35f);
    }

    private void initLabel(int count){
        _label = new Label(Fonts.fontXSmall, count);
        addChild(_label);
        _label.setCenterCoeff(0.45f, 0.5f);
        _label.setY(_picture.y + _picture.getHeight() + 0.05f + _label.getHeight() / 2);
    }
}
