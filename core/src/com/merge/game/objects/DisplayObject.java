package com.merge.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.logic.Tools;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.labels.Label;
import com.merge.game.objects.shader_effects.ShaderEffect;
import com.merge.game.resources.Shaders;

import java.util.ArrayList;

public class DisplayObject {

    protected static final float MAX_ACTIVE_SCALE = 1.15f;
    protected static final float SCALE_EFFECT_SPEED = 0.007f;
    protected float _scaleEffectSpeed = 0;

    public float x, y, height, width;
    protected TextureRegion texture;
    protected DisplayObject parent;
    protected ArrayList<DisplayObject> childs = new ArrayList<>();

    protected float _rotation = 0.0f, rotationSpeed = 0.0f, _scale = 1.0f, _alpha = 1.0f;
    protected float _hotspotX = 0.5f, _hotspotY = 0.5f;
    protected float _R = 1.0f, _G = 1.0f, _B = 1.0f;

    protected ShaderProgram _shader = null;
    protected ShaderEffect _shaderEffect = null;

    protected boolean _isToDelete = false;

    public DisplayObject() {}

    public DisplayObject(Texture texture) {
        setTexture(texture);
    }

    public DisplayObject(TextureRegion texture) {
        setTexture(texture);
    }

    public float getX() {
        return parent != null ? (parent.getX() + x) : x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return parent != null ? (parent.getY() + y) : y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setSize(float width, float height){
        setWidth(width);
        setHeight(height);
    }

    public void setPosition(float x, float y){
        setX(x);
        setY(y);
    }

    protected void setScale(float scale) {
        _scale = scale;
    }

    public float getScale() {
        return _scale;
    }

    public float getAlpha() {
        return _alpha * (haveParent() ? parent.getAlpha() : 1.0f);
    }

    public void setAlpha(float alpha) {
        _alpha = alpha;
        if(_alpha > 1){
            _alpha = 1;
        }
        if (_alpha < 0){
            _alpha = 0;
        }

        _hasColor = _R != 1 || _G != 1 || _B != 1 || _alpha != 1;
    }

    public boolean isToDelete() {
        return _isToDelete;
    }

    public void setToDelete() {
        _isToDelete = true;
    }

    private boolean haveParent(){
        return parent != null;
    }

    public void moveX(float x) {
        this.x += x;
    }

    public void moveY(float y){
        this.y += y;
    }

    public float getParentWidth(){
        return parent != null ? parent.getWidth() : Gdx.graphics.getWidth();
    }

    public float getParentHeight(){
        return parent != null ? parent.getHeight() : Gdx.graphics.getHeight();
    }

    public void addChild(DisplayObject child){
        if(child != null && !childs.contains(child)){
            child.parent = this;
            childs.add(child);
        }
    }

    public void removeChild(DisplayObject child){
        if(childs.contains(child)){
           childs.remove(child);
        }

        if(child != null){
            child.parent = null;
        }
    }

    public void setSizeOfParent() {
        setWidth(getParentWidth());
        setHeight(getParentHeight());
    }

    public void scaleToSquareSize(float size) {
        scaleToFit(size / getParentWidth(), size / getParentHeight());
    }

    public void scaleToWidth(float coeff){
        setWidth(coeff * getParentWidth());
        setHeight(texture.getRegionHeight() * (getWidth() / texture.getRegionWidth()));
    }

    public void scaleToHeight(float coeff){
        setHeight(coeff * getParentHeight());
        setWidth(texture.getRegionWidth() * getHeight()/texture.getRegionHeight());
    }

    public void scaleToFit(float maxCoeffX, float maxCoeffY){
        scaleToWidth(maxCoeffX);
        float parentHeight = getParentHeight();
        if((getHeight() / parentHeight) > maxCoeffY){
            scaleToHeight(maxCoeffY);
        }
    }

    public void setCenterCoeff(float coeffX, float coeffY){
        setCenterCoeffX(coeffX);
        setCenterCoeffY(coeffY);
    }

    public boolean isTouched(){
        return Input.isTouched() && isMouseOver();
    }

    public boolean isJustTouched(){
        return Input.isJustTouched() && isMouseOver();
    }

    public boolean isMouseOver() {
        return Tools.isRectTouched(getX(), getY(), getWidth(), getHeight());
    }

    public void setCenterPosition(float x, float y) {
        setCenterX(x);
        setCenterY(y);
    }

    public void update() {

        //TODO this
        if(rotationSpeed != 0){
            _rotation += rotationSpeed * Globals.deltaTime;
        }

        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).update();
        }
    }

    private boolean _hasColor = false;
    private float r, g, b, a;

    public void draw(SpriteBatch spriteBatch) {

        if(_hasColor){
            Color color = spriteBatch.getColor();
            r = color.r;
            g = color.g;
            b = color.b;
            a = color.a;
            spriteBatch.setColor(r * _R, g * _G, b * _B, a * _alpha);
        }

        drawObject(spriteBatch);

        if(childs != null){
            drawChilds(spriteBatch);
        }

        if(_hasColor){
            spriteBatch.setColor(r, g, b, a);
        }
    }

    protected void drawObject(SpriteBatch spriteBatch){
        //TODO this
        if(texture != null){

            if(_shader != null){
                spriteBatch.end();
                spriteBatch.setShader(_shader);
                updateShader();
                spriteBatch.begin();
            }

            spriteBatch.draw(texture, getX(), Gdx.graphics.getHeight() - (getY() + height), _hotspotX * width, _hotspotY * height, width, height, _scale, _scale, -_rotation);

            if(_shader != null){
                spriteBatch.end();
                spriteBatch.setShader(Shaders.shaderDefault);
                spriteBatch.begin();
            }
        }
    }

    private void updateShader() {
        _shader.begin();
        put("u_u1", getTexture().getU());
        put("u_u2", getTexture().getU2());
        put("u_v1", getTexture().getV());
        put("u_v2", getTexture().getV2());

        if(_shaderEffect != null){
            _shaderEffect.updateShader();
        }

        _shader.end();
    }

    private void put(String location, float value) {
        _shader.setUniformf(_shader.getUniformLocation(location), value);
    }

    protected void drawChilds(SpriteBatch spriteBatch){
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).draw(spriteBatch);
        }
    }

    public void destroy(){
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).destroy();
        }
    }

    protected void setTexture(Texture texture) {
        setTexture(new TextureRegion(texture));
    }

    protected void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    private void setCenterX(float x) {
        setX(x);
        moveX(-getWidth()/2);
    }


    private void setCenterY(float y) {
        setY(y);
        moveY(-getHeight()/2);
    }

    public void setCenterCoeffX(float coeffX) {
        setX(coeffX * getParentWidth());
        moveX(-getWidth()/2);
    }

    public void setCenterCoeffY(float coeffY) {
        setY(coeffY * getParentHeight());
        moveY(-getHeight()/2);
    }

    public void setShaderEffect(ShaderEffect shaderEffect) {
        _shaderEffect = shaderEffect;
        if(_shaderEffect == null){
           setShader(null);
        } else {
           setShader(_shaderEffect.getShader());
        }
    }

    private void setShader(ShaderProgram shader) {
        _shader = shader;
    }


    public void setColor(int r, int g, int b, float alpha) {
        _R = r;
        _G = g;
        _B = b;
        _alpha = alpha;
        _hasColor = _R != 1 || _G != 1 || _B != 1 || _alpha != 1;
    }

    protected float getOwnAlpha() {
        return _alpha;
    }

    public DisplayObject createObject(TextureRegion texture, float scaleWidth, float cx, float cy){
        DisplayObject object = new DisplayObject(texture);
        addChild(object);
        object.scaleToWidth(scaleWidth);
        object.setCenterCoeff(cx, cy);
        return object;
    }

    protected Button createButton(TextureRegion texture, float widthCoeff, float cx, float cy) {
        Button button = new Button(texture);
        addChild(button);
        button.scaleToWidth(widthCoeff);
        button.setCenterCoeff(cx, cy);
        return button;
    }

    public Label createLabel(BitmapFont font, String text, float cx, float cy) {
        Label label = new Label(font, text);
        addChild(label);
        label.setCenterCoeff(cx, cy);
        return label;
    }

}
