package com.merge.game.logic;

import com.badlogic.gdx.Gdx;

public class Tools {

    public static boolean isRectTouched(float x, float y, float w, float h){
        return rectsOverlap(x, y, w, h, Gdx.input.getX(), Gdx.input.getY(), 1, 1);
    }

    public static boolean rectsOverlap(float x1, float y1, float w1, float h1,
                                       float x2, float y2, float w2, float h2) {
        if (x1 > x2 + w2 || x1 + w1 < x2)
            return false;
        if (y1 > y2 + h2 || y1 + h1 < y2)
            return false;
        return true;
    }

    public static int randomInt(int i) {
        return (int) Math.floor(Math.random() * i);
    }

    //от min до max не включительно
    public static int randomInt(int min, int max) {
        return min + randomInt(max - min);
    }
}
