package com.merge.game.logic.player_data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.merge.game.resources.GameSound;

import java.util.ArrayList;

public class Player {

    private static Player _instance = null;

    public static Player get(){
        if(_instance == null){
            _instance = new Player();
        }

        return _instance;
    }

    private static Preferences _preferences;

    private int _score = 0;
    private int _level = 1;
    private int _energyCount = 30;
    private long _energyTime = System.currentTimeMillis();
    private boolean _exists = false;

    private ArrayList<String> _itemsType = new ArrayList<>(); //i_j_тип 1=merge 2=generate 0=null
    private ArrayList<String> _itemsMergeType = new ArrayList<>(); //тип_уровень_generateType 0 null

    public void init() {

        _preferences = Gdx.app.getPreferences(PreferencesParams.GAME_NAME);
        _exists = _preferences.getBoolean(PreferencesParams.EXISTS, false);
        _score = _preferences.getInteger(PreferencesParams.SCORE, 0);
        _level = _preferences.getInteger(PreferencesParams.LEVEL, 0);
        _energyCount = _preferences.getInteger(PreferencesParams.ENERGY_COUNT, 30);
        _energyTime = _preferences.getLong(PreferencesParams.ENERGY_TIME, System.currentTimeMillis());

        //TODO как бы выглядела грядка

        //TODO хардкод уберу
        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                _itemsType.add(index, _preferences.getString(PreferencesParams.ITEM_TYPE + index, i + j + "0"));
                _itemsMergeType.add(index, _preferences.getString(PreferencesParams.ITEM_MERGE_TYPE + index, "000"));
            }
        }

        GameSound.setMusicState(_preferences.getBoolean(PreferencesParams.MUSIC, true), false);
        GameSound.setSoundState(_preferences.getBoolean(PreferencesParams.SOUND, true), false);
    }

    public void savePreferences() {
    }
}
