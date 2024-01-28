package com.merge.game.objects.shader_effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.merge.game.resources.Shaders;
import com.merge.game.resources.textures.Textures;

public class ShaderEffectObjectLightning extends ShaderEffect{

    private float _dt, _speed;

    public ShaderEffectObjectLightning(float speed) {
        setShader(Shaders.shaderObjectLightning);
        _speed = speed;
    }

    @Override
    public void updateShader() {
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE1);
        Textures.texDisplacement.bind();
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        _dt += _speed;
        if(_dt > Math.PI * 2.0f){
            _dt -= Math.PI * 2.0f;
        }

        put("u_dt", _dt);
        put("u_texture_disp", 1);
    }
}
