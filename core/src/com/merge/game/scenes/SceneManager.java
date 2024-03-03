package com.merge.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.merge.game.logic.Globals;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.WindowGui;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.TextureItems;

public class SceneManager {

    private static SceneManager _instance = null;

    public static SceneManager get(){
        if(_instance == null){
            _instance = new SceneManager();
        }

        return _instance;
    }

    private int _currentSceneType = SceneType.SCENE_NULL;
    private int _prevSceneType = SceneType.SCENE_NULL;
    private int _nextSceneType = SceneType.SCENE_NULL;

    private boolean _sceneIsChanched = false;

    private Scene _currentScene;

    private DisplayObject _fade;
    private static final float ALPHA_SPEED = 0.04f;

    public SceneManager() {
        _fade = new DisplayObject(TextureItems.fade);
        _fade.setSizeOfParent();
    }

    public void setScene(int sceneType){

        if(_nextSceneType == SceneType.SCENE_NULL && _currentScene != null){
            _nextSceneType = sceneType;
        }else {
            _nextSceneType = SceneType.SCENE_NULL;
            _prevSceneType = _currentSceneType;
            _currentSceneType = sceneType;

            if(_currentScene != null){
                _currentScene.destroy();
            }

            _currentScene = SceneFactory.getScene(sceneType);
            _sceneIsChanched = true;
        }

    }

    public void update() {
        updateSceneChanging();
        WindowGui.get().update();
        GameSound.update();
        if(_currentScene != null){
            _currentScene.update();
        }
    }

    public void draw(SpriteBatch batch) {

        if(_fade != null && _fade.getAlpha() < 1){
            if(_currentScene != null){
                _currentScene.draw(batch);
            }
        }

        if(_fade != null){
            _fade.draw(batch);
        }

    }

    private void updateSceneChanging() {

        if(_nextSceneType != SceneType.SCENE_NULL){
            if(_fade.getAlpha() < 1){
                _fade.setAlpha(_fade.getAlpha() + ALPHA_SPEED * Globals.deltaTime);
                if(_fade.getAlpha() >= 1.0f){
                    setScene(_nextSceneType);
                    _fade.setAlpha(1.0f);
                }
            }
        }else{
            if(_fade.getAlpha() > 0){
                _fade.setAlpha(_fade.getAlpha() - ALPHA_SPEED * Globals.deltaTime);
                if (_fade.getAlpha() <= 0){
                    _fade.setAlpha(0);
                }
            }
        }
    }
}
