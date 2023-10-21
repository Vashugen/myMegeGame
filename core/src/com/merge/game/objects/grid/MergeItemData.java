package com.merge.game.objects.grid;

public class MergeItemData {

    private static final int KETTLE_FINAL_LEVEL = 5;

    public static int getFinalLevel(MergeItem item){
        switch (item.getGenerateType()){
            case GenerateItemType.KETTLE:
                return KETTLE_FINAL_LEVEL;
        }

        return 1;
    }

}
