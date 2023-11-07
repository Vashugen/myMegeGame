package com.merge.game.scenes;

import com.merge.game.objects.Background;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.TextureItems;
import com.merge.game.resources.textures.Textures;

public class SceneStart extends Scene {

    private DisplayObject _background;
    private DisplayObject _logo;
    private Button _startButton;

    public SceneStart() {
        super();
        GameSound.playBackgroundMusic(GameSound.mainTheme);
        createBackGround();
        createLogo();
        createButton();
    }

    private void createBackGround() {
        _background = new Background(Textures.TexBackground);
        addChild(_background);
    }

    private void createLogo() {

    }

    private void createButton(){
        _startButton = new Button(TextureItems.buttonStart);
        addChild(_startButton);
        _startButton.scaleToWidth(0.25f);
        _startButton.setCenterCoeff(0.5f, 0.8f);
    }

    @Override
    public void update() {
        super.update();
        if(_startButton.isPressed()){
            SceneManager.get().setScene(SceneType.SCENE_MAIN);
        }
    }
}
