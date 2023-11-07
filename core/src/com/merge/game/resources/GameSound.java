package com.merge.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.merge.game.logic.Globals;
import com.merge.game.logic.player_data.Player;

import java.util.ArrayList;

public class GameSound {

    public static final float VOLUME  = 0.75f;
    public static final float SOUND_VOLUME = 1.0f;
    public static final float SOUND_VOLUME_BUTTON = 0.3f;

    public static Music mainTheme;
    public static Sound button, merge1, generate1;

    private static boolean _soundOn = true, _musicOn = true;
    private static Music _currentPlayingMusic = null;

    private static ArrayList<Sound> _lockedSounds = new ArrayList<>();
    private static ArrayList<Sound> _playingSounds = new ArrayList<>();

    private static final float TIMER_SOUND_FALL = 15.0f;
    public static float timerSoundFall = TIMER_SOUND_FALL;

    public static void initMusic(){
        mainTheme = loadMusic("MainTheme.mp3");
    }

    public static void initSound(){
        button = loadSound("button.mp3");
        merge1 = loadSound("merge1.mp3");
        //generate1 = loadSound("generate1.mp3");
    }

    public static void update() {
        _lockedSounds.clear();

        timerSoundFall -= Globals.deltaTime;
        if (timerSoundFall <= 0)
            timerSoundFall = 0;
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

    public static void playSound(final Sound sound, final float volMod) {
        if (!isSoundOn())
            return;

        if (_lockedSounds.contains(sound)){
            return;
        }

        _lockedSounds.add(sound);
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (_playingSounds != null && _playingSounds.contains(sound)){
                            try {
                                sound.stop();
                                _playingSounds.remove(sound);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        sound.play(volMod);

                        if (_playingSounds != null){
                            _playingSounds.add(sound);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static Sound loadSound(String name) {
        return Gdx.audio.newSound(Gdx.files.internal("sound/" + name));
    }
}
