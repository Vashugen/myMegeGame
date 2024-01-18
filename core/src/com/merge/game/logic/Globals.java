package com.merge.game.logic;

import java.util.ArrayList;

public class Globals {

    public static int screenWidth, screenHeight;
    public static float deltaTime = 1.0f;
    public static float offsetX, offsetY, itemSize, offsetTop, offsetBottom;

    public static final ArrayList<String> generateExists = new ArrayList<>();

    public static void updateGenerateList(String generateType) {
        boolean exists = false;
        for (int i = 0; i < generateExists.size(); i++) {
            if (generateExists.get(i) == generateType){
                exists = true;
                break;
            }
        }

        if(!exists){
            generateExists.add(generateType);
        }
    }
}
