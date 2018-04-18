package ua.com.meraya.media;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import ua.com.meraya.Setting;

public class Media {
    private String musicName = "music1.ogg";

    private AssetManager assetManager;

    public Media(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void playSound(String name){
        if (Setting.isSoundPlay) {
            Sound sound = assetManager.get("sounds/" + name, Sound.class);
            sound.play();
        }
    }

    public void stopSound(String name){
        if (Setting.isSoundPlay) {
            Sound sound = assetManager.get("sounds/" + name, Sound.class);
            sound.stop();
        }
    }

    public void playSound(String name, float volume){
        if (Setting.isSoundPlay) {
            Sound sound = assetManager.get("sounds/" + name, Sound.class);
            sound.loop(volume);
        }
    }

    public void playMusic(String name, boolean loop){
      if (Setting.isMusicPlay) {
          musicName = name;
          Music music = assetManager.get("musics/" + name, Music.class);
          music.setLooping(loop);
          music.setVolume(0.4f);
          music.play();
      }
    }

    public void stopMusic(String name){
        if (Setting.isMusicPlay){
            Music music = assetManager.get("musics/" + name, Music.class);
            music.stop();
        }
    }

    public String getMusicName(){
        return musicName;
    }

    public void addMusic(String name){
        assetManager.load("musics/" + name, Music.class);
    }

    public void removeMusic(String name){
        assetManager.unload("musics/" + name);
    }

    public boolean update(){
        return assetManager.update();
    }
}
