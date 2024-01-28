package com.merge.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shaders {

    public static ShaderProgram shaderDefault;
    public static ShaderProgram shaderObjectLightning;

    public static void loadShaders() {
        ShaderProgram.pedantic = false;

        shaderDefault = SpriteBatch.createDefaultShader();
        shaderObjectLightning = loadShader("GemLightning");
    }

    private static ShaderProgram loadShader(String name) {
        return new ShaderProgram(
                Gdx.files.internal("shaders/" + name + ".vert"),
                Gdx.files.internal("shaders/" + name + ".frag"));
    }

}
