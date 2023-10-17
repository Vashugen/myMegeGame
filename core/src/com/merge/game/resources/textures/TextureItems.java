package com.merge.game.resources.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureItems {

    public static TextureRegion buttonStart, generateKettle, score, gold, level, trash, taskField, taskButton;
    public static TextureRegion[] gridCell, kettle1, kettle2, kettle3;

    public static void initItems(){
        initFields();
        initMergeItems();
        initPanels();
    }

    private static void initFields() {
        Texture texture = Textures.fields1;
        buttonStart = new TextureRegion(texture, 312, 452, 239, 112);

        gridCell = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            gridCell[i] = new TextureRegion(texture, 497 + (i * 168), 715, 168, 168);
        }
    }

    private static void initMergeItems() {
        Texture texture = Textures.items;
        generateKettle = new TextureRegion(texture, 11, 9, 120, 120);

        //in const
        kettle1 = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            kettle1[i] = new TextureRegion(texture, 14 + i * 112, 11, 112, 112);
        }

        kettle2 = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            kettle2[i] = new TextureRegion(texture,14 + i * 112, 146, 112, 112);
        }
    }

    private static void initPanels() {
        Texture texture = Textures.items;
        score = new TextureRegion(texture, 582, 13, 109, 109);
        gold = new TextureRegion(texture, 707, 13,109, 109);
        level = new TextureRegion(texture, 834, 13, 138, 109);
        trash = new TextureRegion(texture, 581, 137, 109, 109);
        taskField = new TextureRegion(texture, 706, 137, 284, 116);
        taskButton = new TextureRegion(texture, 728, 278, 240, 110);
    }

}
