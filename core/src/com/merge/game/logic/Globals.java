package com.merge.game.logic;

import com.merge.game.objects.grid.MergeItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Globals {

    public static int screenWidth, screenHeight;
    public static float deltaTime = 1.0f;
    public static float offsetX, offsetY, itemSize, offsetTop, offsetBottom;

    public static ArrayList<String> generateExists = new ArrayList<>();
    public static HashMap<String, Integer> mergeLevelList = new HashMap<>();

    public static void updateGenerateList(String generateType) {
        boolean exists = false;
        for (int i = 0; i < generateExists.size(); i++) {
            if (generateExists.get(i).equals(generateType)){
                exists = true;
                break;
            }
        }

        if(!exists){
            generateExists.add(generateType);
        }

        if(!mergeLevelList.containsKey(generateType)){
            mergeLevelList.put(generateType, 1);
        }
    }

    public static boolean generatorExists(String type) {

        for (int i = 0; i < generateExists.size(); i++) {
            if(generateExists.get(i).equals(type)){
                return true;
            }
        }

        return false;
    }

    public static void updateMergeLevelList(MergeItem mergeItem) {
        int maxLevel = mergeLevelList.get(mergeItem.getGenerateType());
        if(mergeItem.getLevel() > maxLevel){
            mergeLevelList.put(mergeItem.getGenerateType(), mergeItem.getLevel());
        }
    }

    public static int getMaxExistsLevel(String generateItemType) {
        if(mergeLevelList.containsKey(generateItemType)){
            return mergeLevelList.get(generateItemType);
        }

        return 1;
    }
}
