package ua.com.meraya.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boontaran.MessageListener;
import com.boontaran.games.StageGame;

import ua.com.meraya.MainEngine;


public class MainMenuScreen extends StageGame {

    public static final int ON_PLAY = 1;
    public static final int ON_BACK = 2;
    public static final int ON_SHARE = 3;
    public static final int ON_RATE = 4;
    public static final int ON_SETTINGS = 5;
    public static final int ON_ABOUT = 6;
    public static final int ON_ACHIVE = 7;

    SettingsScreen settingsScreen;
    AboutScreen aboutScreen;
    AchiveScreen achiveScreen;

    private Image title;
    private ImageButton playButton, shareButton, rateButton, settingsButton, aboutButton, achiveButton;
    private int space = 70;
    private Group group;

    public MainMenuScreen() {
        settingsScreen = new SettingsScreen(getWidth(), getHeight());
        aboutScreen = new AboutScreen(getWidth(), getHeight());
        achiveScreen = new AchiveScreen(getWidth(), getHeight());
        group = new Group();

        Image bg = new Image(MainEngine.atlas.findRegion("level_bg"));
        addBackground(bg, true, false);

        title = new Image(MainEngine.atlas.findRegion("title"));
        addChild(title);

        centerActorX(title);
        title.setY(getHeight());

        MoveByAction move = new MoveByAction();
        move.setAmount(0, -title.getHeight() * 1.4f);
        move.setDuration(0.4f);
        move.setInterpolation(Interpolation.swingOut);
        move.setActor(title);

        title.addAction(Actions.delay(0.5f, move));

        playButton = new ImageButton(
                new TextureRegionDrawable(MainEngine.atlas.findRegion("play_btn")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("play_btn_down"))
        );

        addChild(playButton);
        centerActorXY(playButton);
        playButton.moveBy(0, -40);

        AlphaAction alphaActionPlay = new AlphaAction();
        alphaActionPlay.setActor(playButton);
        alphaActionPlay.setDuration(0.8f);
        alphaActionPlay.setAlpha(1);

        playButton.setColor(1, 1, 1, 0);
        playButton.addAction(Actions.delay(0.8f, alphaActionPlay));

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playButton.clearActions();
                onClickedPlay();
                MainEngine.media.playSound("click.ogg");
            }
        });

        shareButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("share")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("share_down")));

        shareButton.setSize(64, 64);
        shareButton.setX(0);
        shareButton.setY(20);


        shareButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                call(ON_SHARE);
                MainEngine.media.playSound("click.ogg");

            }
        });

        rateButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("rate")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("rate_down")));


        rateButton.setSize(64, 64);
        rateButton.setX(shareButton.getX() + rateButton.getWidth() + space);
        rateButton.setY(20);

        rateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                call(ON_RATE);
                MainEngine.media.playSound("click.ogg");
            }
        });


        settingsButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("settings_btn")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("settings_btn_down")));

        settingsButton.setSize(64, 64);
        settingsButton.setX(rateButton.getX() + settingsButton.getWidth() + space);
        settingsButton.setY(20);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showSettingsScreen();
            }
        });

        settingsScreen.addListener(new MessageListener(){
            @Override
            protected void receivedMessage(int message, Actor actor) {
                if (message == SettingsScreen.ON_BACK){
                    MainEngine.media.playSound("click.ogg");
                    hideSettingsScreen();
              }
           }
        });


        achiveButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("achive_btn")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("achive_btn_down")));


        achiveButton.setSize(64, 64);
        achiveButton.setX(settingsButton.getX() + achiveButton.getWidth() + space);
        achiveButton.setY(20);

        achiveButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showAchiveScreen();
            }
        });

        achiveScreen.addListener(new MessageListener(){
            @Override
            protected void receivedMessage(int message, Actor actor) {
                if (message == AboutScreen.ON_BACK){
                    MainEngine.media.playSound("click.ogg");
                    hideAchiveScreen();
                }
            }
        });

        aboutButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("about_btn")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("about_btn_down")));


        aboutButton.setSize(64, 64);
        aboutButton.setX(achiveButton.getX() + aboutButton.getWidth() + space);
        aboutButton.setY(20);

        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showAboutScreen();
            }
        });

        aboutScreen.addListener(new MessageListener(){
            @Override
            protected void receivedMessage(int message, Actor actor) {
                if (message == AboutScreen.ON_BACK){
                    MainEngine.media.playSound("click.ogg");
                    hideAboutScreen();
                }
            }
        });




        group.addActor(shareButton);
        group.addActor(rateButton);
        group.addActor(settingsButton);
        group.addActor(achiveButton);
        group.addActor(aboutButton);


        addChild(group);
        group.setX((getWidth() / 2) - 300);

        AlphaAction alphaActionGroup = new AlphaAction();
        alphaActionGroup.setActor(group);
        alphaActionGroup.setDuration(0.8f);
        alphaActionGroup.setAlpha(1);

        group.setColor(1, 1, 1, 0);
        group.addAction(Actions.delay(1.2f, alphaActionGroup));

    }

    private void onClickedPlay() {
        playButton.setTouchable(Touchable.disabled);
        playButton.addAction(Actions.alpha(0, 0.3f));
        group.addAction(Actions.alpha(0, 0.3f));
        title.addAction(Actions.moveTo(title.getX(), getHeight(), 0.5f, Interpolation.swingIn));

        delayCall("delay1", 0.7f);
    }

    public boolean keyUp(int keyCode) {
        if ((keyCode == Input.Keys.ESCAPE || keyCode == Input.Keys.BACK) && SettingsScreen.enabled) {
            hideSettingsScreen();
            return true;
        }else if ((keyCode == Input.Keys.ESCAPE || keyCode == Input.Keys.BACK) && !SettingsScreen.enabled) {
            call(ON_BACK);
            return true;
        }
        return super.keyUp(keyCode);
    }

    protected void onDelayCall(String code) {
        if (code.equals("delay1")) {
            call(ON_PLAY);
        }else if (code.equals("SettingDestroy")){
            removeOverlayChild(settingsScreen);
        }else if(code.equals("AboutDestroy")){
            removeOverlayChild(aboutScreen);
        }else if (code.equals("AchiveDestroy")){
            removeOverlayChild(achiveScreen);
        }
    }


    private void showSettingsScreen() {
        MainEngine.media.playSound("click.ogg");
        setUntouchButn();
        addOverlayChild(settingsScreen);
        settingsScreen.start();
    }

    private void hideSettingsScreen() {
        settingsScreen.hide();
        delayCall("SettingDestroy", 0.2f);
        setTouchButn();
    }

    private void showAboutScreen() {
        MainEngine.media.playSound("click.ogg");
        setUntouchButn();
        addOverlayChild(aboutScreen);
        aboutScreen.start();
    }

    private void hideAboutScreen() {
        aboutScreen.hide();
        delayCall("AboutDestroy", 0.2f);
        setTouchButn();
    }

    private void showAchiveScreen() {
        MainEngine.media.playSound("click.ogg");
        setUntouchButn();
        addOverlayChild(achiveScreen);
        achiveScreen.start();
    }

    private void hideAchiveScreen() {
        achiveScreen.hide();
        delayCall("AchiveDestroy", 0.2f);
        setTouchButn();
    }



    private void setUntouchButn() {
        playButton.setTouchable(Touchable.disabled);
        shareButton.setTouchable(Touchable.disabled);
        rateButton.setTouchable(Touchable.disabled);
        settingsButton.setTouchable(Touchable.disabled);
        achiveButton.setTouchable(Touchable.disabled);
        aboutButton.setTouchable(Touchable.disabled);
    }

    private void setTouchButn() {
        playButton.setTouchable(Touchable.enabled);
        shareButton.setTouchable(Touchable.enabled);
        rateButton.setTouchable(Touchable.enabled);
        settingsButton.setTouchable(Touchable.enabled);
        achiveButton.setTouchable(Touchable.enabled);
        aboutButton.setTouchable(Touchable.enabled);
    }
}

