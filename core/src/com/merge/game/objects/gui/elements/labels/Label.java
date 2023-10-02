package com.merge.game.objects.gui.elements.labels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.merge.game.objects.DisplayObject;

public class Label extends DisplayObject {

    protected String string;

    private BitmapFont _font;
    private GlyphLayout _layout;

    private float _x, _y;

    public Label(float x, float y, float coeffSize, Color color) {
        _layout = new GlyphLayout();
        generateFont(coeffSize, color);
        _x = x;
        _y = y;
    }

    public float getX(){
        return parent != null ? (parent.getX() + _x) : _x;
    }

    public float getY(){
        return parent != null ? (parent.getY() + _y) : _y;
    }

    public void setX(float x) {
        _x = x;
    }

    public void setY(float y) {
        _y = y;
    }

    @Override
    public float getWidth() {
        return _layout.width;
    }

    @Override
    public void setCenterCoeff(float coeffX, float coeffY) {
        setCenterCoeffX(coeffX);
        setCenterCoeffY(coeffY);
    }

    private void setCenterCoeffX(float coeffX) {
        _x = coeffX * getParentWidth();
        _x += -_layout.width / 2;
    }

    private void setCenterCoeffY(float coeffY) {
        _y = coeffY * getParentHeight();
        _y += -_layout.height / 2;
    }

    private void generateFont(float coeffSize, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/comic/comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (Gdx.graphics.getWidth() * coeffSize);
        parameter.color = color;
        _font = generator.generateFont(parameter);
        generator.dispose();
    }

    public void setString(String string){
        this.string = string;
        _layout.setText(_font, string);
    }

    public void setString(int string) {
        this.string = "" + string;
        _layout.setText(_font, "" + string);
    }

    public void draw(SpriteBatch spriteBatch) {
        _font.draw(spriteBatch, string, getX(), Gdx.graphics.getHeight() - getY());
    }
}
