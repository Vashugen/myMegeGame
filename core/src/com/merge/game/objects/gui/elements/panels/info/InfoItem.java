package com.merge.game.objects.gui.elements.panels.info;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.player_data.Player;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.textures.TextureItems;

public class InfoItem extends DisplayObject {

    private Label _label;
    private int _infoType;

    public InfoItem(DisplayObject parent, float scaleToWidth, int infoType) {
        parent.addChild(this);
        _infoType = infoType;
        setTexture(getInfoTexture());
        scaleToWidth(scaleToWidth);

        initLabel();
    }

    private void initLabel() {
        _label = createLabel(Fonts.fontXXSmall, "", 0, 0);
        updateLabel();
    }

    public void updateLabel() {
        _label.setText(getInfoCount() + "");
        _label.setCenterCoeff(1.5f,0.5f);
    }

    private int getInfoCount() {
        return _infoType == 0 ? Player.get().getGold() : Player.get().getScore();
    }

    private TextureRegion getInfoTexture() {
        return _infoType == 0 ? TextureItems.gold : TextureItems.score;
    }
}
