package com.merge.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.merge.game.logic.Globals;
import com.merge.game.objects.gui.elements.labels.Label;

public class Fonts {

    private static final float BASE_DEVICE_WIDTH = 768.0f;
    public static BitmapFont fontXSmall, fontMedium, fontLarge;

    @SuppressWarnings("unused")
    public static void loadFonts() {
        float m = Globals.screenWidth / BASE_DEVICE_WIDTH;
        fontXSmall = loadFont("comic/comic.ttf", 32 * m, 1);
        fontMedium = loadFont("comic/comic.ttf", 46 * m, 2);
        fontLarge = loadFont("comic/comic.ttf", 62 * m, 3);
    }

    private static BitmapFont loadFont(String fontName, float size, float border) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontName));
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "\u0000ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!?.',:()$/+-";
/*        if (Vocab.Lang == Vocab.RUSSIAN)
            parameter.characters += "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщьыъэюя";*/

        parameter.size = (int) size;
        parameter.borderWidth = border;
        parameter.borderColor = new Color(0x492C13FF);
        parameter.spaceY = (int) (-size * 0.25f);
        parameter.minFilter = Texture.TextureFilter.Nearest;
        parameter.magFilter = Texture.TextureFilter.Nearest;
        BitmapFont result = generator.generateFont(parameter);
        generator.dispose();
        return result;
    }

    public static void dispose() {
        try {
            Label.dispose();
            fontLarge.dispose();
            fontMedium.dispose();
            fontXSmall.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fontLarge = null;
        fontMedium = null;
        fontXSmall = null;
    }
}
