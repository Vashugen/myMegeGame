package com.merge.game.logic.player_data;

import com.badlogic.gdx.Preferences;
import com.merge.game.logic.Tools;
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
    private int _gold = 30;
    private long _energyTime = System.currentTimeMillis();
    private boolean _exists = false;

    private ArrayList<String> _itemsType = new ArrayList<>(); //i_j_тип 1=merge 2=generate 0=null
    private ArrayList<String> _itemsMergeType = new ArrayList<>(); //тип_уровень_generateType 0 null

    public void init() {

        _preferences = Tools.getPreferences();
        _exists = _preferences.getBoolean(PreferencesParams.EXISTS, false);
        _score = _preferences.getInteger(PreferencesParams.SCORE, 30);
        _level = _preferences.getInteger(PreferencesParams.LEVEL, 0);
        _gold = _preferences.getInteger(PreferencesParams.GOLD, 30);
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

/*        Preferences preferences = Tools.getPreferences();

        preferences.putBoolean(PreferencesParams.EXISTS, true);

        for (int i = 0; i < PlayerItem.ITEM_COUNT; i++)
            preferences.putInteger(PreferencesParams.ITEM + i, _Items.get(i));

        for (int i = 0; i < Tutorials.NUM_OF_TUTORIALS; i++)
            preferences.putBoolean(PreferencesParams.TUTORIAL + i, _Tutorials.get(i));

        preferences.putBoolean(PreferencesParams.VOTED, _IsRated);
        preferences.putInteger(PreferencesParams.CHALLENGES_COUNT, _ChallengesCount);
        preferences.putInteger(PreferencesParams.DAILY_REWARD_DAY, _DailyRewardAddedDay);
        preferences.putLong(PreferencesParams.REGULAR_REWARD, _LastRegularRewardTime);
        preferences.putInteger(PreferencesParams.TREASURE_MAPS, _TreasureMaps);
        preferences.putBoolean(PreferencesParams.MUSIC, GameSound.IsMusicOn());
        preferences.putBoolean(PreferencesParams.SOUND, GameSound.IsSoundOn());
        preferences.flush();*/
    }

    public int getScore() {
        return _score;
    }

    public int getLevel() {
        return _level;
    }

    public int getGold() {
        return _gold;
    }
}
