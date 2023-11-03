package com.merge.game.objects.grid;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.resources.textures.TextureItems;

import java.util.ArrayList;

public class GenerateItemType {

    public static final String KETTLE = "kettle";
    public static final String AMULET = "amulet";

    private static ArrayList<TextureRegion[]> _texturesKettle = new ArrayList();
    private static ArrayList<TextureRegion[]> _texturesAmulet = new ArrayList();

    public static void init(){
        _texturesKettle.add(0, null);
        _texturesKettle.add(1, TextureItems.kettle1);
        _texturesKettle.add(2, TextureItems.kettle2);
        _texturesKettle.add(3, TextureItems.kettle3);
        _texturesKettle.add(4, TextureItems.kettle4);
        _texturesKettle.add(5, TextureItems.kettle5);

        _texturesAmulet.add(0, null);
        _texturesAmulet.add(1, TextureItems.amulet1);
    }

    public static TextureRegion[] getTexture(String generateType, int level){
        switch (generateType){
            case KETTLE:
                return _texturesKettle.get(level);
            case AMULET:
                level = 1;
                return _texturesAmulet.get(level);
        }

        return null;
    }

    public static String getGenerateType(String generateType, int itemType){
        switch (generateType){
            case KETTLE:
                switch (itemType){
                    case 1:
                        return AMULET;
                }
        }
        return null;
    }

/*    public static TextureRegion[] getTypeName(String type, int level) {
        String name = type + level;
        return TextureItems.name;
    }*/
}
