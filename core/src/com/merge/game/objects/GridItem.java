package com.merge.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.resources.GameSound;

public class GridItem extends GridObject {

    private static final float MAX_ACTIVE_SCALE = 1.15f;
    private static final float SCALE_EFFECT_SPEED = 0.007f;

    private float _scaleEffectSpeed = 0;

    private boolean _isMoving = false;
    private float _startMoveX = 0, _startMoveY = 0;
    private float _moveX = 0, _moveY = 0;

    protected boolean _isActive = false;

    public GridItem(TextureRegion texture) {
        super(texture);
    }

    public GridItem(TextureRegion texture, float x, float y, float width, float height, int gridX, int gridY, int type, int level, String generateType) {
        super(texture, x, y, width, height, gridX, gridY, type, level, generateType);
    }

    @Override
    public void update() {
        updateScale();
    }

    public boolean isActive() {
        return _isActive;
    }

    public void setActive(boolean state) {
        _isActive = state;
    }

    public boolean isMoving() {
        return _isMoving;
    }

    public void setMoving(boolean state) {
        _isMoving = state;
    }

    public float getMoveX() {
        return _moveX - _startMoveX;
    }

    public float getMoveY() {
        return _moveY - _startMoveY;
    }

    public void startMove() {
        _isMoving = true;
        _startMoveX = _moveX = Input.GetTouchX();
        _startMoveY = _moveY = Input.GetTouchY();
        setActive(true);
        //setScale(SCALE_AFTER_PRESS);
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

}
