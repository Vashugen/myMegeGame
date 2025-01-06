package com.merge.game.scenes;

import com.merge.game.objects.Background;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.TextureItems;
import com.merge.game.resources.textures.Textures;

public class SceneStart extends Scene{

    private Background _background;
    private DisplayObject _mainPanel, _panelShop, _panelPlay, _panelRewards;
    private Button _shop, _play, _magic;

    public SceneStart() {
        GameSound.playBackgroundMusic(GameSound.mainTheme);
        initBackground();
        initMainPanel();
        initPanels();
        initButtons();
    }

    private void initBackground() {
        _background = new Background(Textures.TexBackgroundMain);
        addChild(_background);
    }

    private void initMainPanel() {
        _mainPanel = new DisplayObject(TextureItems.startScenePanel);
        addChild(_mainPanel);
        _mainPanel.scaleToFit(0.7f, 0.6f);
        _mainPanel.setCenterCoeff(0.5f, 0.5f);
        _mainPanel.setY(0);
    }

    private void initPanels() {

        _panelShop = new DisplayObject();
        _mainPanel.addChild(_panelShop);
        _panelShop.setSizeOfParent();
        _panelShop.setWidth(_panelShop.getParentWidth() / 3);
        _panelShop.setX(0);

        _panelPlay = new DisplayObject();
        _mainPanel.addChild(_panelPlay);
        _panelPlay.setSizeOfParent();
        _panelPlay.setWidth(_panelShop.getParentWidth() / 3);
        _panelPlay.setX(_panelShop.getWidth());

        _panelRewards = new DisplayObject();
        _mainPanel.addChild(_panelRewards);
        _panelRewards.setSizeOfParent();
        _panelRewards.setWidth(_panelShop.getParentWidth() / 3);
        _panelRewards.setX(_panelShop.getWidth() + _panelPlay.getWidth());
    }

    private void initButtons() {
        DisplayObject frame = new DisplayObject();
        _mainPanel.addChild(frame);
        frame.setSizeOfParent();
        frame.setWidth(frame.getParentWidth() * 0.85f);
        //frame.scaleToWidth(0.7f);
        frame.setCenterCoeff(0.5f, 0.5f);
        _shop = frame.createButton(TextureItems.startSceneShop, 0.22f, 0.27f, 0.7f);
        _play = frame.createButton(TextureItems.startScenePlay, 0.22f, 0.5f, 0.7f);
        _magic = frame.createButton(TextureItems.startSceneMagic, 0.22f, 0.73f, 0.7f);
        /*initShop();
        initPlay();
        initRewards();*/
    }

    private void initShop() {
        _shop = new Button(TextureItems.startSceneShop);
        _panelShop.addChild(_shop);
        _shop.scaleToFit(0.9f, 0.9f);
        _shop.setCenterCoeff(0.5f, 0.5f);
    }


    private void initPlay() {
        _play = new Button(TextureItems.startScenePlay);
        _panelPlay.addChild(_play);
        _play.scaleToFit(0.9f, 0.9f);
        _play.setCenterCoeff(0.5f, 0.5f);
    }

    private void initRewards() {
        _magic = new Button(TextureItems.startSceneMagic);
        _panelRewards.addChild(_magic);
        _magic.scaleToFit(0.9f, 0.9f);
        _magic.setCenterCoeff(0.5f, 0.5f);
    }

    @Override
    public void update() {
        super.update();
        if(_play.isPressed()){
            SceneManager.get().setScene(SceneType.SCENE_GAME);
        }
    }
}
