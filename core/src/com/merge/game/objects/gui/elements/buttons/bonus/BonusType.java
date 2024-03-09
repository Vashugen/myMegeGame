package com.merge.game.objects.gui.elements.buttons.bonus;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.logic.game_logic.Economics;
import com.merge.game.resources.textures.TextureItems;

public class BonusType {

    private static final int GOLD = 0;
    public static final int FIX_GENERATE = 1;
    public static final int MAX_ITEM = 2;
    public static final int MAGIC_GENERATOR = 3;

    public static final int BONUS_QUANTITY = 3;
    public static final int MAX_ITEM_CREATE_QUANTITY = 2;

    public static TextureRegion getItemTexture(int itemId) {
        switch (itemId){
            case GOLD:
                return TextureItems.gold;
            case FIX_GENERATE:
                return TextureItems.bonusFixGenerate;
            case MAX_ITEM:
                return TextureItems.bonusMaxItem;
            case MAGIC_GENERATOR:
                return TextureItems.bonusMagicGenerator;
        }
        return null;
    }


    public static int getItemCost(int bonusType) {
        switch (bonusType){
            case FIX_GENERATE:
                return Economics.COST_FIX_GENERATOR;
            case MAX_ITEM:
                return Economics.COST_MAX_ITEM;
            case MAGIC_GENERATOR:
                return Economics.COST_MAGIC_GENERATOR;
        }

        return 0;
    }
}
