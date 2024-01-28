package com.merge.game.objects.shader_effects;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public abstract class ShaderEffect {
    private ShaderProgram _shader = null;

    protected void setShader(ShaderProgram shader) {
        _shader = shader;
    }

    public ShaderProgram getShader() {
        return _shader;
    }

    public abstract void updateShader();

    protected void put(String location, float value) {
        _shader.setUniformf(_shader.getUniformLocation(location), value);
    }

    protected void puti(String location, int value) {
        _shader.setUniformi(_shader.getUniformLocation(location), value);
    }
}
