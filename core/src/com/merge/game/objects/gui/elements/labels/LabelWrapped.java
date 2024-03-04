package com.merge.game.objects.gui.elements.labels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;


public class LabelWrapped extends Label{

   private float _WrapWidth = 0;

    public LabelWrapped(BitmapFont font, String text, float wrapWidth) {
        super(font);
        _WrapWidth = wrapWidth;
        setText(text);
    }

    public LabelWrapped(String text, BitmapFont font, float wrapWidth) {
        super(font);
        _WrapWidth = wrapWidth;
        setText(text);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setWidth(_WrapWidth);
        setHeight(new GlyphLayout(getFont(), text, _color, _WrapWidth, Align.center, true).height);
    }

    @Override
    protected void drawObject(SpriteBatch spr) {
        getFont().setColor(_color.r, _color.g, _color.b, _color.a * getAlpha());
        getFont().draw(spr, getText(), getX(), Gdx.graphics.getHeight() - getY(), _WrapWidth, Align.center, true);
        getFont().setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
