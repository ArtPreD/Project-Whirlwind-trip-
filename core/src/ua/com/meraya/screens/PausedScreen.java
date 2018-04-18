package ua.com.meraya.screens;

import com.badlogic.gdx.math.Interpolation;
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


public class PausedScreen extends Group {
    public static final int ON_RESUME = 1;
    public static final int ON_QUIT = 2;
    public static final int ON_RETRY = 3;

    private Image title, muteMusic, muteSound;
    private ImageButton resume, quit, retry;
    private int space;

    private float w, h;

    public PausedScreen(float w, float h) {
        this.w = w;
        this.h = h;
        space = 15;


        title = new Image(MainEngine.atlas.findRegion("paused"));
        title.setHeight(h / 1.1f);
        title.setWidth(w / 2.5f);
        title.setX((w - title.getWidth()) / 2);
        title.setY(h);
        addActor(title);

        quit = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("quit_btn")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("quit_btn_down")));
        addActor(quit);
        quit.setY((h  - title.getHeight()) + quit.getHeight() - space);
        quit.setX((w - quit.getWidth()) / 2);
        quit.setColor(1, 1, 1, 0);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fire(new MessageEvent(ON_QUIT));
                MainEngine.media.playSound("click.ogg");
            }
        });

        retry = new ImageButton(
                new TextureRegionDrawable(MainEngine.atlas.findRegion("retry_btn")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("retry_btn_down")));
        addActor(retry);

        retry.setY(quit.getY() + retry.getHeight()+ space);
        retry.setX((w - retry.getWidth()) / 2);

        retry.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fire(new MessageEvent(ON_RETRY));
                MainEngine.media.playSound("click.ogg");
            }
        });

        resume = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("play_btn")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("play_btn_down")));
        addActor(resume);
        resume.setY(retry.getY() + resume.getHeight() + space);
        resume.setX((w - resume.getWidth()) / 2);
        resume.setColor(1, 1, 1, 0);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fire(new MessageEvent(ON_RESUME));
                    MainEngine.media.playSound("click.ogg");
            }
        });


        if (Setting.isMusicPlay) {
            muteMusic = new Image(MainEngine.atlas.findRegion("music_btn_on"));
        } else {
            muteMusic = new Image(MainEngine.atlas.findRegion("music_btn_off"));
        }
        muteMusic.setX((w - quit.getWidth()) / 2 - space - 10);
        muteMusic.setY(resume.getY() + resume.getHeight() + space);
        muteMusic.setColor(1, 1, 1, 0);
        muteMusic.addAction(Actions.alpha(1, 0.2f));
        addActor(muteMusic);
        muteMusic.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Setting.isMusicPlay) {
                    MainEngine.media.stopMusic(MainEngine.media.getMusicName());
                    muteMusic.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("music_btn_off")));
                    MainEngine.data.setMusicMute(false);
                    Setting.isMusicPlay = false;
                }else if (!Setting.isMusicPlay){
                    MainEngine.data.setMusicMute(true);
                    Setting.isMusicPlay = true;
                    MainEngine.media.playMusic(MainEngine.media.getMusicName(), true);
                    muteMusic.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("music_btn_on")));
                }
            }
        });

        if (Setting.isSoundPlay) {
            muteSound = new Image(MainEngine.atlas.findRegion("sound_btn_on"));
        } else {
            muteSound = new Image(MainEngine.atlas.findRegion("sound_btn_off"));
        }
        muteSound.setX((w - quit.getWidth()) / 2 + muteSound.getWidth() - space + 10);
        muteSound.setY(resume.getY() + resume.getHeight() + space);
        muteSound.setColor(1, 1, 1, 0);
        muteSound.addAction(Actions.alpha(1, 0.2f));
        addActor(muteSound);
        muteSound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Setting.isSoundPlay) {
                    muteSound.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("sound_btn_off")));
                    MainEngine.data.setMusicMute(false);
                    Setting.isSoundPlay = false;
                }else if (!Setting.isSoundPlay){
                    MainEngine.data.setMusicMute(true);
                    Setting.isSoundPlay = true;
                    muteSound.setDrawable(new TextureRegionDrawable(MainEngine.atlas.findRegion("sound_btn_on")));
                }
            }
        });
    }

    public void start(){
        title.setY(h);
        resume.setColor(1, 1, 1, 0);
        quit.setColor(1, 1, 1, 0);
        muteMusic.setColor(1, 1, 1, 0);
        muteSound.setColor(1, 1, 1, 0);

        title.addAction(Actions.moveTo(title.getX(), h - title.getHeight() - 25, 0.5f, Interpolation.swingOut));
        resume.addAction(Actions.alpha(1, 0.3f));
        quit.addAction(Actions.alpha(1, 0.3f));
        retry.addAction(Actions.alpha(1, 0.3f));
        muteMusic.addAction(Actions.alpha(1, 0.3f));
        muteSound.addAction(Actions.alpha(1, 0.3f));
    }

    public void hide(){
        title.addAction(Actions.moveTo(title.getX(), h, 0.5f, Interpolation.swingIn));
        resume.addAction(Actions.alpha(0, 0.3f));
        quit.addAction(Actions.alpha(0, 0.3f));
        retry.addAction(Actions.alpha(0, 0.3f));
        muteMusic.addAction(Actions.alpha(0, 0.3f));
        muteSound.addAction(Actions.alpha(0, 0.3f));

    }

}
