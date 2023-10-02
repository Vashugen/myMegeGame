package com.merge.game.resources.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {
    public static Texture TexBackground, TexBackgroundGame;
    public static Texture fields1, items;


    public static void loadTextures(){
        TexBackground = loadTextureMipmap("scenes/start/background.jpg", true);
        TexBackgroundGame = loadTextureMipmap("scenes/game/backgroundGame.jpg", true);
        fields1 = loadTextureMipmap("InterfaceB.png", true);
        items = loadTextureMipmap("scenes/game/MergeItems.png", true);

        TextureItems.initItems();
    }

    private static Texture loadTextureMipmap(String name, boolean useMipMap) {
        Texture texture = new Texture(Gdx.files.internal(name), useMipMap);
        texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
        return texture;
    }

}
