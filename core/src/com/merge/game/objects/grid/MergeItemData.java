package com.merge.game.objects.grid;

public class MergeItemData {

    private static final int KETTLE_FINAL_LEVEL = 5;
    private static final int AMULET_FINAL_LEVEL = 4;
    private static final int POTION_FINAL_LEVEL = 4;
    private static final int BOOK_FINAL_LEVEL = 4;

    public static int getFinalLevel(MergeItem item){

        switch (item.getGenerateType()){
            case GenerateItemType.KETTLE:
                return KETTLE_FINAL_LEVEL;
            case GenerateItemType.AMULET:
                return AMULET_FINAL_LEVEL;
            case GenerateItemType.POTION:
                return POTION_FINAL_LEVEL;
            case GenerateItemType.BOOK:
                return BOOK_FINAL_LEVEL;
        }

        return 1;
    }

}
