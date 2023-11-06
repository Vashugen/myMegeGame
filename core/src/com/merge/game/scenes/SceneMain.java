package com.merge.game.scenes;

import com.merge.game.objects.Background;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.Textures;

public class SceneMain extends Scene{

    private Background _background;

    public SceneMain() {
        GameSound.playBackgroundMusic(GameSound.mainTheme);
        initBackground();
        initShop();
        initPlay();
        initRewards();
    }

    private void initBackground() {
        _background = new Background(Textures.TexBackgroundGame);
        addChild(_background);
    }

    private void initShop() {

    }


    private void initPlay() {

    }

    private void initRewards() {
    }
}
