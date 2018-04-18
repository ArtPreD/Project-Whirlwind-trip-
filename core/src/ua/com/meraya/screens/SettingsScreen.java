package ua.com.meraya.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boontaran.MessageEvent;

import ua.com.meraya.MainEngine;
import ua.com.meraya.Setting;


public class SettingsScreen extends Group {
    public static final int ON_BACK = 1;

    public static boolean enabled = false;

    Image backGround, topBg,muteMusic, muteSound, changeLanguage, labelMusic, lableSound, labelLanguge;
    ImageButton done;

    private float w, h;


    public SettingsScreen(float w, float h) {
        this.w = w;
        this.h = h;

        backGround = new Image(MainEngine.atlas.findRegion("level_list_bg"));
        topBg = new Image(MainEngine.atlas.findRegion("settings_label"));

        done = new ImageButton(
                new TextureRegionDrawable(MainEngine.atlas.findRegion("done_btn")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("done_btn_down")) );
        }

    public void start(){
        enabled = true;
        backGround.setSize(w / 1.5f, h / 1.2f);
        backGround.setX(((w - backGround.getWidth()) / 2));
        backGround.setY(((h - backGround.getHeight()) / 2));
        backGround.setColor(1, 1, 1, 0);
        backGround.addAction(Actions.alpha(1, 0.2f));
        addActor(backGround);

        topBg.setSize(w / 1.6f, h / 4.2f);
        topBg.setX((w - topBg.getWidth()) / 2 - 5);
        topBg.setY(backGround.getHeight() - topBg.getHeight() / 2 +20);
        topBg.setColor(1, 1, 1, 0);
        topBg.addAction(Actions.alpha(1, 0.2f));
        addActor(topBg);

        if (Setting.isMusicPlay) {
            muteMusic = new Image(MainEngine.atlas.findRegion("music_btn_on"));
            labelMusic = new Image(MainEngine.atlas.findRegion("label_music_on"));
            lableSound = new Image(MainEngine.atlas.findRegion("label_sound_on"));
        } else {
            muteMusic = new Image(MainEngine.atlas.findRegion("music_btn_off"));
            labelMusic = new Image(MainEngine.atlas.findRegion("label_music_off"));
            lableSound = new Image(MainEngine.atlas.findRegion("label_sound_off"));
        }
        muteMusic.setX(backGround.getX() + 80);
        muteMusic.setY(backGround.getHeight() - muteMusic.getHeight() * 1.5f);
        muteMusic.setColor(1, 1, 1, 0);
        muteMusic.addAction(Actions.alpha(1, 0.2f));
        addActor(muteMusic);
        muteMusic.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Setting.isMusicPlay) {
                    MainEngine.media.stopMusic(MainEngine.media.getMusicName());
                    muteMusic.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("music_btn_off")));
                    labelMusic.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("label_music_off")));
                    MainEngine.data.setMusicMute(false);
                    Setting.isMusicPlay = false;
                }else if (!Setting.isMusicPlay){
                    MainEngine.data.setMusicMute(true);
                    Setting.isMusicPlay = true;
                    MainEngine.media.playMusic(MainEngine.media.getMusicName(), true);
                    muteMusic.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("music_btn_on")));
                    labelMusic.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("label_music_on")));
                }
            }
        });

        if (Setting.isSoundPlay) {
            muteSound = new Image(MainEngine.atlas.findRegion("sound_btn_on"));
        } else {
            muteSound = new Image(MainEngine.atlas.findRegion("sound_btn_off"));
        }
        muteSound.setX(backGround.getWidth() - 40);
        muteSound.setY(backGround.getHeight() - muteSound.getHeight() * 1.5f);
        muteSound.setColor(1, 1, 1, 0);
        muteSound.addAction(Actions.alpha(1, 0.2f));
        addActor(muteSound);
        muteSound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Setting.isSoundPlay) {
                    muteSound.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("sound_btn_off")));
                    lableSound.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("label_sound_off")));
                    MainEngine.data.setMusicMute(false);
                    Setting.isSoundPlay = false;
                }else if (!Setting.isSoundPlay){
                    MainEngine.data.setMusicMute(true);
                    Setting.isSoundPlay = true;
                    muteSound.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("sound_btn_on")));
                    lableSound.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("label_sound_on")));
                }
            }
        });

        lableSound.setX(muteSound.getX() - muteSound.getWidth() / 2);
        lableSound.setY(muteSound.getY() - muteSound.getHeight() / 2.5f);

        labelMusic.setX(muteMusic.getX() - muteMusic.getWidth() / 2);
        labelMusic.setY(muteMusic.getY() - muteMusic.getHeight() / 2.5f);
        addActor(labelMusic);
        addActor(lableSound);



        done.setPosition(((backGround.getX() + (backGround.getWidth()) / 2) - (done.getWidth() / 2)), backGround.getY() + 80);
        done.setColor(1, 1, 1, 0);
        done.addAction(Actions.alpha(1, 0.2f));
        addActor(done);
        done.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fire(new MessageEvent(ON_BACK));
            }
        });
    }

    public void hide() {
        enabled = false;
        backGround.addAction(Actions.alpha(0, 0.2f));
        done.addAction(Actions.alpha(0, 0.2f));
        muteMusic.addAction(Actions.alpha(0, 0.2f));
        muteSound.addAction(Actions.alpha(0, 0.2f));
        labelMusic.addAction(Actions.alpha(0, 0.2f));
        lableSound.addAction(Actions.alpha(0, 0.2f));
        topBg.addAction(Actions.alpha(0, 0.2f));
    }
}
