package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObject;

public class GridObject extends GameObject {

    protected int gridX, gridY;

    protected int _maxCount = 10;
    protected int _level = 0;
    protected String _generateType;

    public GridObject(TextureRegion texture) {
        super(texture);
    }

    public GridObject(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int level, String generateType){
        super(texture);
        _level = level;
        _generateType = generateType;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setGridPosition(gridX, gridY);
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getLevel() {
        return _level;
    }

    public void placeAtGrid(int x, int y) {
        setGridPosition(x, y);
        setPosition(getXByGridX(x), getYByGridY(y));
    }

    private void setGridPosition(int x, int y) {
        setGridPositionX(x);
        setGridPositionY(y);
    }

    private void setGridPositionX(int x) {
        gridX = x;
    }

    private void setGridPositionY(int y) {
        gridY = y;
    }


    private float getXByGridX(int x) {
        return Globals.offsetX + x * Globals.itemSize;
    }


    private float getYByGridY(int y) {
        return Globals.offsetY + y * Globals.itemSize;
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

    public void updateLevel() {
        _level ++;
        //setTexture(getTexture());
    }

    /*private TextureRegion getTexture() {
        TextureRegion[] textureRegion =  GenerateItemType.getTypeName(_generateType, _level);
        return textureRegion[type];
    }*/

    public String getGenerateType() {
        return _generateType;
    }
}
