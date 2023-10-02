package com.merge.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.merge.game.resources.GameSound;

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
        GameSound.update();
        if(_currentScene != null){
            _currentScene.update();
        }
    }

    public void draw(SpriteBatch batch) {
        if(_currentScene != null){
            _currentScene.draw(batch);
        }
    }
}
