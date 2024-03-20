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

    public InfoItem(DisplayObject parent, float scaleToWidth, int infoType) {
        parent.addChild(this);
        setTexture(getInfoTexture(infoType));
        scaleToWidth(scaleToWidth);

        initLabel();
    }

    private void initLabel() {
        _label = createLabel(Fonts.fontMedium, "", 0, 0);
        updateLabel();
    }

    private void updateLabel() {
        _label.setText(Player.get().getGold() + "");
        _label.
    }

    private TextureRegion getInfoTexture(int infoType) {
        return infoType == 0 ? TextureItems.gold : TextureItems.score;
    }
}
