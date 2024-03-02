package com.merge.game.objects.gui;

import com.merge.game.objects.gui.windows.Window;

import java.util.ArrayList;

public class WindowGui {
    private static WindowGui _instance = null;

    public static WindowGui get(){
        return _instance == null ? new WindowGui() : _instance;
    }

    private ArrayList<Window> _windowList;

    public  void addWindow(Window window){
        _windowList.add(0, window);
    }

}
