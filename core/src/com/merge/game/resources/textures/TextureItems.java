package com.merge.game.resources.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureItems {

    public static TextureRegion buttonStart, generateKettle, score, gold, level, trash, clear,
            taskField, taskAbledButton, taskDisabledButton, fade, broken, gridPanel, window, window2, buttonClose;
    public static TextureRegion bonusPanel, bonusSquare, bonusCount, bonusFixGenerate, bonusMaxItem, bonusMagicGenerator, bonusRandomGenerator;
    public static TextureRegion mainScenePanel, mainScenePlay, mainSceneShop, mainSceneRewards;
    public static TextureRegion[] numbers;
    public static TextureRegion[] gridCell, kettle1, kettle2, kettle3, kettle4, kettle5;
    public static TextureRegion[] amulet1, amulet2, amulet3, amulet4;
    public static TextureRegion[] potion1, potion2, potion3, potion4;
    public static TextureRegion[] box1, box2, box3, box4;
    public static TextureRegion[] book1, book2, book3, book4;
    public static TextureRegion[] magic1;

    public static void initItems(){
        initFields();
        initMain();
        initBonus();
        initGrid();
        initMergeItems();
        initGameItems();
        initTasks();
        initNumbers();
    }

    private static void initFields() {
        Texture texture = Textures.fields;
        buttonStart = new TextureRegion(texture, 312, 452, 239, 112);

        fade = new TextureRegion(texture, 3, 741, 28, 28);
        window = new TextureRegion(texture, 526, 738, 497, 286);
        window2 = new TextureRegion(texture, 14, 259, 281, 481);
        buttonClose = new TextureRegion(texture, 309, 211, 200, 200);
        texture = Textures.gridPanel;
        gridPanel = new TextureRegion(texture);
    }

    private static void initMain(){
        Texture texture = Textures.mainScene;
        mainScenePanel = new TextureRegion(texture, 0, 209, 832, 320);
        mainScenePlay = new TextureRegion(texture, 0, 0, 210, 210);
        mainSceneShop = new TextureRegion(texture, 210 * 1, 0, 210, 210);
        mainSceneRewards = new TextureRegion(texture, 210 * 2, 0, 210, 210);
    }

    private static void initMergeItems() {
        initKettle();
        initAmulet();
        initPotion();
        initBox();
        initBook();
        initMagic();
        initEffects();
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

    private static void initAmulet(){

        Texture texture = Textures.amulet;

        //in const
        amulet1 = new TextureRegion[3];
        amulet1[0] = new TextureRegion(texture, 0, 0, 112, 112);
        for (int i = 1; i <= 1; i++) {
            amulet1[i] = new TextureRegion(texture, i * 112, 0, 112, 112);
        }

        amulet2 = new TextureRegion[3];
        amulet2[0] = new TextureRegion(texture, 0, 132, 112, 112);
        for (int i = 1; i <= 1; i++) {
            amulet2[i] = new TextureRegion(texture,i * 112, 132, 112, 112);
        }

        amulet3 = new TextureRegion[3];
        amulet3[0] = new TextureRegion(texture, 0, 264, 112, 112);
        for (int i = 1; i <= 1; i++) {
            amulet3[i] = new TextureRegion(texture, i * 112, 264, 112, 112);
        }

        amulet4 = new TextureRegion[3];
        amulet4[0] = new TextureRegion(texture, 0, 396, 112, 112);
        for (int i = 1; i <= 1; i++) {
            amulet4[i] = new TextureRegion(texture, i * 112, 396, 112, 112);
        }
    }

    private static void initPotion(){

        Texture texture = Textures.potion;

        //in const
        potion1 = new TextureRegion[2];
        potion1[0] = new TextureRegion(texture, 0, 0, 112, 112);
        for (int i = 1; i <= 1; i++) {
            potion1[i] = new TextureRegion(texture, i * 112, 0, 112, 112);
        }

        potion2 = new TextureRegion[2];
        potion2[0] = new TextureRegion(texture, 0, 132, 112, 112);
        for (int i = 1; i <= 1; i++) {
            potion2[i] = new TextureRegion(texture,i * 112, 132, 112, 112);
        }

        potion3 = new TextureRegion[2];
        potion3[0] = new TextureRegion(texture, 0, 264, 112, 112);
        for (int i = 1; i <= 1; i++) {
            potion3[i] = new TextureRegion(texture, i * 112, 264, 112, 112);
        }

        potion4 = new TextureRegion[2];
        potion4[0] = new TextureRegion(texture, 0, 396, 112, 112);
        for (int i = 1; i <= 1; i++) {
            potion4[i] = new TextureRegion(texture, i * 112, 396, 112, 112);
        }
    }

    private static void initBox(){

        Texture texture = Textures.box;

        //in const
        box1 = new TextureRegion[2];
        box1[0] = new TextureRegion(texture, 0, 0, 112, 112);
        for (int i = 1; i <= 1; i++) {
            box1[i] = new TextureRegion(texture, i * 112, 0, 112, 112);
        }

        box2 = new TextureRegion[2];
        box2[0] = new TextureRegion(texture, 0, 132, 112, 112);
        for (int i = 1; i <= 1; i++) {
            box2[i] = new TextureRegion(texture,i * 112, 132, 112, 112);
        }

        box3 = new TextureRegion[2];
        box3[0] = new TextureRegion(texture, 0, 264, 112, 112);
        for (int i = 1; i <= 1; i++) {
            box3[i] = new TextureRegion(texture, i * 112, 264, 112, 112);
        }

        box4 = new TextureRegion[2];
        box4[0] = new TextureRegion(texture, 0, 396, 112, 112);
        for (int i = 1; i <= 1; i++) {
            box4[i] = new TextureRegion(texture, i * 112, 396, 112, 112);
        }
    }

    private static void initBook(){

        Texture texture = Textures.book;

        //in const
        book1 = new TextureRegion[2];
        book1[0] = new TextureRegion(texture, 0, 0, 112, 112);
        for (int i = 1; i <= 1; i++) {
            book1[i] = new TextureRegion(texture, i * 112, 0, 112, 112);
        }

        book2 = new TextureRegion[2];
        book2[0] = new TextureRegion(texture, 0, 132, 112, 112);
        for (int i = 1; i <= 1; i++) {
            book2[i] = new TextureRegion(texture,i * 112, 132, 112, 112);
        }

        book3 = new TextureRegion[2];
        book3[0] = new TextureRegion(texture, 0, 264, 112, 112);
        for (int i = 1; i <= 1; i++) {
            book3[i] = new TextureRegion(texture, i * 112, 264, 112, 112);
        }

        book4 = new TextureRegion[2];
        book4[0] = new TextureRegion(texture, 0, 396, 112, 112);
        for (int i = 1; i <= 1; i++) {
            book4[i] = new TextureRegion(texture, i * 112, 396, 112, 112);
        }
    }

    private static void initMagic() {
        magic1 = new TextureRegion[1];
        magic1[0] = bonusMagicGenerator;
    }

    private static void initEffects(){
        Texture texture = Textures.panels;
        broken = new TextureRegion(texture, 565, 0, 120, 120);
    }

    private static void initGameItems() {

        Texture texture = Textures.items;
        score = new TextureRegion(texture, 560, 0, 109, 109);
        gold = new TextureRegion(texture, 672, 0,109, 109);
        level = new TextureRegion(texture, 784, 0, 116, 116);
        trash = new TextureRegion(texture, 896, 0, 109, 109);
        clear = new TextureRegion(texture, 560, 132, 112, 112);
    }

    private static void initTasks() {
        Texture texture = Textures.panels;
        taskField = new TextureRegion(texture, 197, 616, 114, 107);
        taskAbledButton = new TextureRegion(texture, 309, 448, 246, 121);
        taskDisabledButton = new TextureRegion(texture, 316, 568, 231, 114);
    }

    private static void initBonus() {
        Texture texture = Textures.panels;
        bonusPanel = new TextureRegion(texture, 9, 219, 171, 390);
        bonusSquare = new TextureRegion(texture, 803,6, 120, 120);
        bonusCount = new TextureRegion(texture, 685, 0, 120, 120);
        bonusFixGenerate = new TextureRegion(texture, 0, 852, 170, 170);
        bonusMaxItem = new TextureRegion(texture, 171, 852, 170, 170);
        bonusMagicGenerator = new TextureRegion(texture, 342, 852, 170, 170);
        bonusRandomGenerator = new TextureRegion(texture, 342, 852, 170, 170);
    }

    private static void initGrid() {
        Texture texture = Textures.panels;

        gridCell = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            gridCell[i] = new TextureRegion(texture, 497 + (i * 168), 715, 168, 168);
        }
    }

    private static void initNumbers() {
        Texture texture = Textures.panels;
        numbers = new TextureRegion[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = new TextureRegion(texture, 184 + (i * 120), 219, 120, 120);
        }
    }
}