package com.merge.game.objects.gui.windows;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.gui.elements.labels.LabelWrapped;

public class WindowText extends Window {

    private static final float TEXT_W_COEF = 0.7f;
    private static final float TEXT_CX_COEF = 0.5f;
    private static final float TEXT_CY_COEF = 0.565f;

    public WindowText(String title, BitmapFont font, String text) {
        setTitle(title);
        LabelWrapped label = new LabelWrapped(font, text, getWidth() * TEXT_W_COEF);
        addChild(label);
        label.setCenterCoeff(TEXT_CX_COEF, TEXT_CY_COEF);
    }
}
