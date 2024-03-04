package com.merge.game.objects.gui.elements.labels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.merge.game.objects.DisplayObject;

public class Label extends DisplayObject {

    private BitmapFont _font;
    private String _text;
    private float _x, _y;

    private GlyphLayout _layout;

    protected Color _color = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    public Label(BitmapFont font){
        _layout = new GlyphLayout();
        setFont(font);
        setText("");
    }

    public Label(float x, float y, float coeffSize, Color color) {
        _layout = new GlyphLayout();
        generateFont(coeffSize, color);
        _x = x;
        _y = y;
    }

    public Label(BitmapFont font, String string) {
        _layout = new GlyphLayout();
        setFont(font);
        setText(string);
    }

    public Label(BitmapFont font, int string) {
        _layout = new GlyphLayout();
        setFont(font);
        setText(string);
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

    public static void dispose() {
    }

    public BitmapFont getFont() {
        return _font;
    }

    private void setFont(BitmapFont font) {
        _font = font;
    }

    public void setCenterCoeffX(float coeffX) {
        _x = coeffX * getParentWidth();
        _x += -_layout.width / 2;
    }

    public void setCenterCoeffY(float coeffY) {
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

    public String getText() {
        return _text;
    }

    public void setText(String string){
        _text = string;
        _layout.setText(_font, _text);
    }

    public void setText(int string) {
        this._text = "" + string;
        _layout.setText(_font, "" + string);
    }

    public void draw(SpriteBatch spriteBatch) {
        _font.draw(spriteBatch, _text, getX(), Gdx.graphics.getHeight() - getY());
    }
}
