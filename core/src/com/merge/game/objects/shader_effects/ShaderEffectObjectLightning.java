package com.merge.game.objects.shader_effects;

import com.merge.game.resources.Shaders;

public class ShaderEffectObjectLightning extends ShaderEffect{

    private float _dt, _speed;

    public ShaderEffectObjectLightning(float speed) {
        setShader(Shaders.shaderObjectLightning);
    }

    @Override
    public void updateShader() {

    }
}
