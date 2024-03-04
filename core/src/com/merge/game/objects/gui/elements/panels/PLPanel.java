package com.merge.game.objects.gui.elements.panels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.Fonts;

public class PLPanel extends DisplayObject {

    private DisplayObject _picture;
    private Label _label;

    public void init(TextureRegion texture, int count){
        initPicture(texture);
        initLabel(count);
    }

    public Label getLabel() {
        return _label;
    }

    public void setLabel(String string) {
        _label.setText(string);
    }

    public void setLabel(int string) {
        _label.setText(string);
    }

    private void initPicture(TextureRegion texture) {
        _picture = new DisplayObject(texture);
        addChild(_picture);
        _picture.scaleToFit(0.5f, 0.5f);
        _picture.setCenterCoeff(0.4f, 0.5f);
    }

    private void initLabel(int count){
        _label = new Label(Fonts.fontXSmall, "");
        addChild(_label);
        _label.setText(count);
        float labelCoeff = 0.45f + 0.02f + (_picture.getWidth() / getWidth()) + ((_label.getWidth() / 2) / getWidth());
        _label.setCenterCoeff(labelCoeff, 0.5f);
        _label.setX(_picture.x + _picture.getWidth() + 0.02f + _label.getWidth() / 2);
        //float labelScoreCoeffX = 0.45f + 0.1f + ((labelScore.getWidth() / 2) / labelScore.getParentWidth());
    }

    public void updateLabel(int quantity) {
        _label.setText(quantity);
    }
}
