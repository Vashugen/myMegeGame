package com.merge.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.merge.game.logic.player_data.Player;

public class GameSound {
    public static final float VOLUME  = 0.75f;

    public static Music mainTheme;
    public static Sound merge1, generate1;

    private static boolean _soundOn = true, _musicOn = true;
    private static Music _currentPlayingMusic = null;

    public static void initMusic(){
        mainTheme = loadMusic("MainTheme.mp3");
    }

    public static void initSound(){
        merge1 = loadSound("merge1.mp3");
        //generate1 = loadSound("generate1.mp3");
    }

    public static boolean isMusicOn(){
        return _musicOn;
    }

    public static boolean isSoundOn(){
        return _soundOn;
    }

    public static void playBackgroundMusic(Music music){

        if(!_musicOn){
            return;
        }

        if(_currentPlayingMusic != null){
            if(_currentPlayingMusic == music){
                return;
            }else {
                _currentPlayingMusic.stop();
            }
        }

        _currentPlayingMusic = music;
        _currentPlayingMusic.setVolume(VOLUME);
        _currentPlayingMusic.setLooping(true);
        _currentPlayingMusic.play();

    }

    private static Music loadMusic(String name){
        return Gdx.audio.newMusic(Gdx.files.internal("music/" + name));
    }

    public static void setMusicState(boolean state, boolean save) {
        try{
            _musicOn = state;
            if(_musicOn){
                if(_currentPlayingMusic != null){
                    _currentPlayingMusic.setVolume(VOLUME);
                    _currentPlayingMusic.play();
                }
            }

            if(!_musicOn && _currentPlayingMusic != null){
                _currentPlayingMusic.pause();
            }

            if(save){
                Player.get().savePreferences();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void setSoundState(boolean state, boolean save) {
        _soundOn = state;
        if(save){
            Player.get().savePreferences();
        }
    }

    public static void update() {
    }

    private static Sound loadSound(String name) {
        return Gdx.audio.newSound(Gdx.files.internal("sound/" + name));
    }
}
