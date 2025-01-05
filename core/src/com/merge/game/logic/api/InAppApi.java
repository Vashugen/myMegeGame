package com.merge.game.logic.api;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.game_logic.Economics;
import com.merge.game.resources.Vocab;
import com.merge.game.resources.textures.TextureItems;

public class InAppApi {

    public static final String ID_GOLD_1_DOLLAR = "gold_1_dollar";
    public static final String ID_GOLD_3_DOLLAR = "gold_3_dollars";
    public static final String ID_GOLD_5_DOLLAR = "gold_5_dollars";

    public static TextureRegion getInAppTexture(String inAppType){
        switch (inAppType){
            case ID_GOLD_1_DOLLAR:
                return TextureItems.coinsBuySmall;
            case ID_GOLD_3_DOLLAR:
                return TextureItems.coinsBuyMedium;
            case ID_GOLD_5_DOLLAR:
                return TextureItems.coinsBuyLarge;
            default:
                return null;
        }
    }

    public static String GetInAppCost(String inApp) {
        switch (Vocab.lang){
            case Vocab.RUSSIAN:
                switch (inApp){
                    case ID_GOLD_1_DOLLAR:
                        return "59р";
                    case ID_GOLD_3_DOLLAR:
                        return "199р";
                    case ID_GOLD_5_DOLLAR:
                        return "349р";
                }
                break;
            default:
                switch (inApp){
                    case ID_GOLD_1_DOLLAR:
                        return "0.99$";
                    case ID_GOLD_3_DOLLAR:
                        return "2.99$";
                    case ID_GOLD_5_DOLLAR:
                        return "4.99$";
                }
                break;
        }

        return "";
    }

    public static int getInAppReward(String inApp) {
        switch (inApp) {
            case ID_GOLD_1_DOLLAR:
                return Economics.GOLD_PER_1_DOLLAR;
            case ID_GOLD_3_DOLLAR:
                return Economics.GOLD_PER_3_DOLLARS;
            case ID_GOLD_5_DOLLAR:
                return Economics.GOLD_PER_5_DOLLARS;
            default:
                return 0;
        }
    }
}
