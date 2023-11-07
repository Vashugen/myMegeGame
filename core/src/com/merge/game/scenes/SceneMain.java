package com.merge.game.scenes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.merge.game.objects.Background;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.TextureItems;
import com.merge.game.resources.textures.Textures;

public class SceneMain extends Scene{

    private Background _background;
    private DisplayObject _panelShop, _panelPlay, _panelRewards;
    private Button _shop, _play, _rewards;

    public SceneMain() {
        GameSound.playBackgroundMusic(GameSound.mainTheme);
        initBackground();
        initPanels();
        initButtons();
    }

    private void initBackground() {
        _background = new Background(Textures.TexBackgroundMain);
        addChild(_background);
    }

    private void initPanels() {
        _panelShop = new DisplayObject();
        addChild(_panelShop);
        _panelShop.setSizeOfParent();
        _panelShop.setWidth(_panelShop.getParentWidth() / 3);
        _panelShop.setX(0);

        _panelPlay = new DisplayObject();
        addChild(_panelPlay);
        _panelPlay.setSizeOfParent();
        _panelPlay.setWidth(_panelShop.getParentWidth() / 3);
        _panelPlay.setX(_panelShop.getWidth());

        _panelRewards = new DisplayObject();
        addChild(_panelRewards);
        _panelRewards.setSizeOfParent();
        _panelRewards.setWidth(_panelShop.getParentWidth() / 3);
        _panelRewards.setX(_panelShop.getWidth() + _panelPlay.getWidth());
    }

    private void initButtons() {
        initShop();
        initPlay();
        initRewards();
    }

    private void initShop() {
        _shop = new Button(TextureItems.mainSceneShop);
        _panelShop.addChild(_shop);
        _shop.scaleToFit(0.9f, 0.9f);
        _shop.setCenterCoeff(0.5f, 0.5f);
    }


    private void initPlay() {
        _play = new Button(TextureItems.mainScenePlay);
        _panelPlay.addChild(_play);
        _play.scaleToFit(0.9f, 0.9f);
        _play.setCenterCoeff(0.5f, 0.5f);
    }

    private void initRewards() {
        _rewards = new Button(TextureItems.mainSceneRewards);
        _panelRewards.addChild(_rewards);
        _rewards.scaleToFit(0.9f, 0.9f);
        _rewards.setCenterCoeff(0.5f, 0.5f);
    }
}
