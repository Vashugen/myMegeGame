package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class GenerateItemType {

    public static final String KETTLE = "kettle";
    public static final String AMULET = "amulet";
    public static final String POTION = "potion";
    public static final String RANDOM = "random";

    private static ArrayList<TextureRegion[]> _texturesKettle = new ArrayList();
    private static ArrayList<TextureRegion[]> _texturesAmulet = new ArrayList();
    private static ArrayList<TextureRegion[]> _texturesPotion = new ArrayList();

    public static void init(){
        _texturesKettle.add(0, null);
        _texturesKettle.add(1, TextureItems.kettle1);
        _texturesKettle.add(2, TextureItems.kettle2);
        _texturesKettle.add(3, TextureItems.kettle3);
        _texturesKettle.add(4, TextureItems.kettle4);
        _texturesKettle.add(5, TextureItems.kettle5);

        _texturesAmulet.add(0, null);
        _texturesAmulet.add(1, TextureItems.amulet1);
        _texturesAmulet.add(2, TextureItems.amulet2);
        _texturesAmulet.add(3, TextureItems.amulet3);
        _texturesAmulet.add(4, TextureItems.amulet4);

        _texturesPotion.add(0, null);
        _texturesPotion.add(1, TextureItems.potion1);
        _texturesPotion.add(2, TextureItems.potion2);
        _texturesPotion.add(3, TextureItems.potion3);
        _texturesPotion.add(4, TextureItems.potion4);
    }

    public static TextureRegion[] getTexture(String generateType, int level){
        switch (generateType){
            case KETTLE:
                return _texturesKettle.get(level);
            case AMULET:
                return _texturesAmulet.get(level);
            case POTION:
                return _texturesPotion.get(level);
        }

        return null;
    }

    public static String getGenerateType(String generateType, int itemType){
        switch (generateType){
            case KETTLE:
                switch (itemType){
                    case 1:
                        return AMULET;
                    case 2:
                        return POTION;
                }
        }
        return null;
    }

    public static int getEnergyByType(String generateType) {
        return 3;
        /*switch (generateType){
            case AMULET:
            case POTION:
            case RANDOM:
                return 10;
        }

        return -1;*/
    }

/*    public static TextureRegion[] getTypeName(String type, int level) {
        String name = type + level;
        return TextureItems.name;
    }*/
}
