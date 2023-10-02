package com.merge.game.logic;

import com.badlogic.gdx.Gdx;

public class Input {

    private static boolean _isTouched = false;
    private static boolean _isJustTouched = false;

    public static void update(){
        _isTouched = Gdx.input.isTouched();
        _isJustTouched = Gdx.input.justTouched();
    }

    public static boolean isTouched(){
        return _isTouched;
    }

    public static boolean isJustTouched(){
        return _isJustTouched;
    }
}
