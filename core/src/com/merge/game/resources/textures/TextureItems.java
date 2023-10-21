package com.merge.game.resources.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureItems {

    public static TextureRegion buttonStart, generateKettle, score, gold, level, trash, taskField, taskButton;
    public static TextureRegion[] gridCell, kettle1, kettle2, kettle3, kettle4, kettle5;

    public static void initItems(){
        initFields();
        initMergeItems();
        initGameItems();
    }

    private static void initFields() {
        Texture texture = Textures.fields;
        buttonStart = new TextureRegion(texture, 312, 452, 239, 112);

        gridCell = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            gridCell[i] = new TextureRegion(texture, 497 + (i * 168), 715, 168, 168);
        }

        taskField = new TextureRegion(texture, 198, 0, 367, 149);
        taskButton = new TextureRegion(texture, 309, 448, 246, 121);
    }

    private static void initMergeItems() {
        initKettle();
    }

    private static void initKettle() {
        Texture texture = Textures.items;
        generateKettle = new TextureRegion(texture, 0, 0, 112, 112);

        //in const
        kettle1 = new TextureRegion[5];
        kettle1[0] = new TextureRegion(texture, 0, 0, 112, 112);
        for (int i = 1; i <= 4; i++) {
            kettle1[i] = new TextureRegion(texture, i * 112, 0, 112, 112);
        }

        kettle2 = new TextureRegion[5];
        kettle2[0] = new TextureRegion(texture, 0, 132, 112, 112);
        for (int i = 1; i <= 4; i++) {
            kettle2[i] = new TextureRegion(texture,i * 112, 132, 112, 112);
        }

        kettle3 = new TextureRegion[5];
        kettle3[0] = new TextureRegion(texture, 0, 264, 112, 112);
        for (int i = 1; i <= 4; i++) {
            kettle3[i] = new TextureRegion(texture, i * 112, 264, 112, 112);
        }

        kettle4 = new TextureRegion[5];
        kettle4[0] = new TextureRegion(texture, 0, 396, 112, 112);
        for (int i = 1; i <= 4; i++) {
            kettle4[i] = new TextureRegion(texture, i * 112, 396, 112, 112);
        }

        kettle5 = new TextureRegion[5];
        kettle5[0] = new TextureRegion(texture, 0, 528, 112, 112);
        for (int i = 1; i <= 4; i++) {
            kettle5[i] = new TextureRegion(texture, i * 112, 528, 112, 112);
        }

    }

    private static void initGameItems() {
        Texture texture = Textures.items;
        score = new TextureRegion(texture, 560, 0, 109, 109);
        gold = new TextureRegion(texture, 672, 0,109, 109);
        level = new TextureRegion(texture, 784, 0, 138, 109);
        trash = new TextureRegion(texture, 896, 0, 109, 109);
    }

}
