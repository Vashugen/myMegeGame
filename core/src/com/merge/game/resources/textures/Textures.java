package com.merge.game.resources.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.merge.game.objects.grid.GenerateItemType;

public class Textures {
    public static Texture TexBackground, TexBackgroundMain, TexBackgroundGame;
    public static Texture fields, panels, mainScene, items, amulet;


    public static void loadTextures(){
        TexBackground = loadTextureMipmap("scenes/start/background.jpg", true);
        TexBackgroundMain =  loadTextureMipmap("scenes/main/background.png", true);
        TexBackgroundGame = loadTextureMipmap("scenes/game/backgroundGame.jpg", true);
        fields = loadTextureMipmap("scenes/game/Fields.png", true);
        panels = loadTextureMipmap("scenes/game/Panels.png", true);
        mainScene = loadTextureMipmap("scenes/main/items.png", true);
        items = loadTextureMipmap("scenes/game/MergeItems.png", true);
        amulet = loadTextureMipmap("scenes/game/AmuletItems.png", true);

        TextureItems.initItems();
        GenerateItemType.init();
    }

    private static Texture loadTextureMipmap(String name, boolean useMipMap) {
        Texture texture = new Texture(Gdx.files.internal(name), useMipMap);
        texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
        return texture;
    }

}
