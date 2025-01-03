package com.merge.game.logic.api;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InAppApi {

    public static final String ID_GOLD_1_DOLLAR = "gold_1_dollar";
    public static final String ID_GOLD_3_DOLLAR = "gold_3_dollars";
    public static final String ID_GOLD_5_DOLLAR = "gold_5_dollars";

    public static TextureRegion getInAppTexture(String inAppType){
        switch (inAppType){
            case ID_GOLD_1_DOLLAR:
                return;
        }
    }
}
