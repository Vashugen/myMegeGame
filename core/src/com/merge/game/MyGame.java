package com.merge.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.logic.Tools;
import com.merge.game.logic.player_data.Player;
import com.merge.game.logic.player_data.PreferencesParams;
import com.merge.game.resources.Fonts;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.Shaders;
import com.merge.game.resources.textures.Textures;
import com.merge.game.scenes.SceneManager;
import com.merge.game.scenes.SceneType;

public class MyGame extends ApplicationAdapter {
	
	SpriteBatch batch;
	private boolean _loaded = false;
	
	@Override
	public void create () {
		_loaded = false;
		initAppSettings();
	}

	private void initAppSettings() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		batch = new SpriteBatch();
		Globals.screenWidth = Gdx.graphics.getWidth();
		Globals.screenHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		if(!_loaded){
			updateLoading();
		}else {
			updateDeltaTime();
			Input.update();
			//какая-то работа с api
			SceneManager.get().update();
			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();
			SceneManager.get().draw(batch);
			batch.end();
		}
	}

	private void updateLoading() {

		Textures.loadTextures();
		Fonts.loadFonts();
		GameSound.initMusic();
		GameSound.initSound();
		Shaders.loadShaders();
		Tools.setPreferences(Gdx.app.getPreferences(PreferencesParams.GAME_NAME));
		Player.get().init();

		SceneManager.get().setScene(SceneType.SCENE_START);
		_loaded = true;

	}

	private void updateDeltaTime() {
		Globals.deltaTime = Gdx.graphics.getDeltaTime() * 60.0f;
		if(Globals.deltaTime > 20.0f){
			Globals.deltaTime = 20.0f;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
