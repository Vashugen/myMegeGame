package com.merge.game.objects.gui.elements.buttons;

import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.panels.TaskArea;
import com.merge.game.resources.textures.TextureItems;

public class TaskButton extends Button {

    public TaskButton() {
        super(TextureItems.taskDisabledButton);
    }

    public void init() {
        scaleToFit(0.25f, 0.25f);
        setCenterCoeff(0.5f, 0.5f);
        setY(((TaskArea) this.parent).getTaskPanel().y + ((TaskArea) this.parent).getTaskPanel().getHeight() + 0.05f); //TODO исправить на coeff
        setDisabled();

    }

    public void setAbled(){
        setTexture(TextureItems.taskAbledButton);
        _isDisabled = false;
    }

    public void setDisabled(){
        setTexture(TextureItems.taskDisabledButton);
        _isDisabled = true;
    }
}
