package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObjectType;

public class GridObject extends DisplayObject {

    protected int _gridX, _gridY;
    protected float _destX, _destY;
    protected boolean _isAtDestX = true, _isAtDestY = true;

    protected int _maxCount = 10;
    protected int _level = 0;
    protected int _type = 0;
    protected String _generateType;

    protected int _gameObjectType;

    public GridObject(TextureRegion texture) {
        super(texture);
    }

    public GridObject(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int type, int level, String generateType){
        super(texture);
        _type = type;
        _level = level;
        _generateType = generateType;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setGridPosition(gridX, gridY);
    }

    public int getGridX() {
        return _gridX;
    }

    public int getGridY() {
        return _gridY;
    }

    public int getLevel() {
        return _level;
    }

    public void placeAtGrid(int x, int y) {
        setGridPosition(x, y);
        setPosition(getXByGridX(x), getYByGridY(y));
    }


    public void setGridPosition(int x, int y) {
        setGridPositionX(x);
        setGridPositionY(y);
    }

    private void setGridPositionX(int x) {
        _gridX = x;
    }

    private void setGridPositionY(int y) {
        _gridY = y;
    }


    protected float getXByGridX(int x) {
        return Globals.offsetX + x * Globals.itemSize;
    }

    protected float getYByGridY(int y) {
        return Globals.offsetY + y * Globals.itemSize;
    }

    public void moveToGrid(int x, int y) {
        setDistanation(getXByGridX(x), getYByGridY(y));
    }

    private void setDistanation(float x, float y) {
        setDestX(x);
        setDestY(y);
    }

    private void setDestX(float x) {
        _destX = x;
        _isAtDestX = false;
    }

    private void setDestY(float y) {
        _destY = y;
        _isAtDestY = false;
    }

    public void setType(int type) {
        this._type = type;
    }

    public int getType() {
        return _type;
    }

    public int getMaxCount() {
        return _maxCount;
    }

    public void setMaxCount(int maxCount) {
        _maxCount = maxCount;
    }

    /*private TextureRegion getTexture() {
        TextureRegion[] textureRegion =  GenerateItemType.getTypeName(_generateType, _level);
        return textureRegion[type];
    }*/

    public String getGenerateType() {
        return _generateType;
    }

    public boolean canBeDeleted() {
        return !(this.getGameObjectType() == GameObjectType.GENERATE && this._generateType.equals(GenerateItemType.KETTLE));
    }

    public int getGameObjectType() {
        return _gameObjectType;
    }

    public boolean isGenerate(){
        return _gameObjectType == GameObjectType.GENERATE;
    }

    public boolean canBeMerge() {
        return _gameObjectType == GameObjectType.MERGE;
    }
}
