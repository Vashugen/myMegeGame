package com.merge.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.logic.Tools;
import com.merge.game.objects.shader_effects.ShaderEffect;

import java.util.ArrayList;
import java.util.Collection;

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
        return _alpha;
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
            spriteBatch.draw(texture, getX(), Gdx.graphics.getHeight() - (getY() + height), _hotspotX * width, _hotspotY * height, width, height, _scale, _scale, -_rotation);
        }
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
}
