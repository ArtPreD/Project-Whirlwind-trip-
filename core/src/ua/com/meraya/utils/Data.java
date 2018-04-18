package ua.com.meraya.utils;

import com.boontaran.DataManager;

public class Data {
    private DataManager manager;

    public static final String PREFERENCE_NAME = "mission_apollo_data";
    public static final String PROGRESS_KEY = "progress";
    public static final String MUSIC_MUTE_KEY = "musicMute";
    public static final String SOUND_MUTE_KEY = "soundMute";

    public Data(){
        manager = new DataManager(PREFERENCE_NAME);
    }

    public int getProgress(){
        return manager.getInt(PROGRESS_KEY, 1);
    }

    public void setProgress(int progress){
        manager.saveInt(PROGRESS_KEY, progress);
    }

    public void setMusicMute(boolean musicMute){
        manager.setBoolean(MUSIC_MUTE_KEY, musicMute);
    }

    public boolean getMusicMute(){
        return manager.getBoolean(MUSIC_MUTE_KEY, true);
    }

    public void setSoundMute(boolean soundMute){
        manager.setBoolean(SOUND_MUTE_KEY, soundMute);
    }

    public boolean getSoundMute(){
        return manager.getBoolean(SOUND_MUTE_KEY, true);
    }

}
