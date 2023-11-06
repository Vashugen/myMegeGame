package com.merge.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.logic.Tools;

import java.util.ArrayList;

public class DisplayObject {

    public float x, y, height, width;
    protected TextureRegion texture;
    protected DisplayObject parent;
    protected ArrayList<DisplayObject> childs = new ArrayList<>();

    protected float rotation = 0.0f, rotationSpeed = 0.0f, _scale = 1.0f;

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
            rotation += rotationSpeed * Globals.deltaTime;
        }

        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).update();
        }
    }

    public void draw(SpriteBatch batch) {

        //TODO this
        if(texture != null){
            batch.draw(texture, getX(), Gdx.graphics.getHeight() - (getY() + height), width, height);
        }

        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).draw(batch);
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

    private void setCenterX(float x) {
        setX(x);
        moveX(-getWidth()/2);
    }


    private void setCenterY(float y) {
        setY(y);
        moveY(-getHeight()/2);
    }

    private void setCenterCoeffX(float coeffX) {
        setX(coeffX * getParentWidth());
        moveX(-getWidth()/2);
    }

    private void setCenterCoeffY(float coeffY) {
        setY(coeffY * getParentHeight());
        moveY(-getHeight()/2);
    }
}
