package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObjectType;
import com.merge.game.resources.textures.TextureItems;

public class MergeItem extends GridObject {

    private static final float MAX_ACTIVE_SCALE = 1.15f;
    private static final float SCALE_EFFECT_SPEED = 0.007f;
    private float _scaleEffectSpeed = 0;

    private boolean _isMoving = false;
    private float _startMoveX = 0, _startMoveY = 0;
    private float _moveX = 0, _moveY = 0;

    private boolean _isDragging = false;
    private float _startDragX = 0, _startDragY = 0;
    private float _dragX = 0, _dragY = 0;

    protected boolean _isActive = false;
    protected boolean _isBroken = false;
    private int _energy = 0;

    public MergeItem(int type, int level, String generateType){
        super(getTexture(type, level, generateType));
        _type = type;
        _level = level;
        _generateType = generateType;
        _gameObjectType = GameObjectType.MERGE;
    }

    public MergeItem(float x, float y, float width, float height, int gridX, int gridY, int type, int level, String generateType, int gameObjectType) {
        super(getTexture(type, level, generateType), x, y, width, height, gridX, gridY, type, level, generateType);
        _gameObjectType = gameObjectType;
        _energy = GenerateItemType.getEnergyByType(generateType);
    }

    public void update() {
        updateScale();
        updateGenerator();
    }

    public boolean isActive() {
        return _isActive;
    }

    public void setActive(boolean state) {
        _isActive = state;
    }

    public float getDragX() {
        return _dragX;
    }

    public float getDragY() {
        return _dragY;
    }

    public boolean match(GridObject gridObject) {
        return this.getLevel() == gridObject.getLevel() && this.getType() == gridObject.getType() && this.getGenerateType().equals(gridObject.getGenerateType());
    }

    public boolean isFinalLevel() {
        return MergeItemData.getFinalLevel(this) == this.getLevel();
    }

    public void updateLevel() {
        _level ++;
        setTexture(getTexture(this.getType(), this.getLevel(), this.getGenerateType()));
        if(isFinalLevel()){
            _gameObjectType = GameObjectType.GENERATE;
            _generateType = GenerateItemType.getGenerateType(getGenerateType(), getType());
        }
    }

    public int getEnergy() {
        return _energy;
    }

    public void decrementEnergy() {
        if(_energy > 0){
            _energy --;
        }
    }

    private static TextureRegion getTexture(int type, int level, String generateType) {
        TextureRegion[] textureRegions = GenerateItemType.getTexture(generateType, level);
        return textureRegions[type];
    }

    private void updateScale() {
        if (_isActive){
            if(getScale() < MAX_ACTIVE_SCALE){
                setScale(getScale() + _scaleEffectSpeed * Globals.deltaTime);
            }
        }else {
            _scaleEffectSpeed = SCALE_EFFECT_SPEED;
            if (_scale > 1.0f){
                setScale(getScale() - _scaleEffectSpeed * Globals.deltaTime);
                if(getScale() < 1.0f){
                    setScale(1.0f);
                }
            }
        }
    }

    private void updateGenerator(){
        if(getGameObjectType() == GameObjectType.GENERATE && _energy == 0){
            brokeItem();
        }else if(!_isBroken) {
            fixItem();
        }
    }

    public void brokeItem() {
        DisplayObject broken = new DisplayObject(TextureItems.broken);
        addChild(broken);
        broken.scaleToFit(0.5f, 0.5f);
        broken.setCenterCoeff(0.9f, 0.1f);
        _isBroken = true;
    }

    private void fixItem() {

    }

    public void startDrag() {
        _isDragging = true;
        _startDragX = _dragX = Input.GetTouchX();
        _startDragY = _dragY = Input.GetTouchY();
    }

    public void moveToGridPosition(int x, int y) {
        setDistanation(x, y);
        setPosition(x, y);
    }

    private void setDistanation(int x, int y) {
        _destX = getXByGridX(x);
        _destY = getYByGridY(y);
    }
}
