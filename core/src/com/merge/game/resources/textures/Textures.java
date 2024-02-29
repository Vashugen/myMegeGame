package com.merge.game.resources.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.merge.game.objects.grid.GenerateItemType;

public class Textures {
    public static Texture texDisplacement;
    public static Texture TexBackground, TexBackgroundMain, TexBackgroundGame;
    public static Texture fields, panels, mainScene, items, amulet, potion, book;
    public static Texture gridPanel;


    public static void loadTextures(){

        texDisplacement = loadTexture("scenes/game/Displacement.jpg", true);
        TexBackground = loadTextureMipmap("scenes/start/background.jpg", true);
        TexBackgroundMain =  loadTextureMipmap("scenes/main/background2.jpg", true);
        TexBackgroundGame = loadTextureMipmap("scenes/game/background.jpg", true);
        fields = loadTextureMipmap("scenes/game/Fields.png", true);
        panels = loadTextureMipmap("scenes/game/Panels.png", true);
        mainScene = loadTextureMipmap("scenes/main/items.png", true);
        items = loadTextureMipmap("scenes/game/MergeItems.png", true);
        amulet = loadTextureMipmap("scenes/game/AmuletItems.png", true);
        potion = loadTextureMipmap("scenes/game/PotionItems.png", true);
        book = loadTextureMipmap("scenes/game/BookItems.png", true);
        gridPanel = loadTextureMipmap("scenes/game/gridPanel.jpg", true);

        TextureItems.initItems();
        GenerateItemType.init();
    }

    private static Texture loadTexture(String path, boolean smooth) {
        Texture texture = new Texture(Gdx.files.internal(path));
        texture.setFilter(Texture.TextureFilter.Linear, smooth ? Texture.TextureFilter.Linear : Texture.TextureFilter.Nearest);
        return texture;
    }

    private static Texture loadTextureMipmap(String name, boolean useMipMap) {
        Texture texture = new Texture(Gdx.files.internal(name), useMipMap);
        texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
        return texture;
    }

}
