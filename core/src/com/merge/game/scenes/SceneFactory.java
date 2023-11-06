package com.merge.game.scenes;

public class SceneFactory {

    public static Scene getScene(int sceneType){

        switch (sceneType){
            case SceneType.SCENE_START:
                return new SceneStart();
            case SceneType.SCENE_GAME:
                return new SceneGame();
            case SceneType.SCENE_MAIN:
                return new SceneMain();
            default:
                throw new Error("Сцена не найдена");
        }
    }
}
