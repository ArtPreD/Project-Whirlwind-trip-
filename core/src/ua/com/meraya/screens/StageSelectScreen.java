package ua.com.meraya.screens;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boontaran.MessageListener;
import com.boontaran.games.StageGame;

import ua.com.meraya.MainEngine;
import ua.com.meraya.Setting;


public class StageSelectScreen extends StageGame {
    public static final int ON_BACK = 1;
    public static final int ON_OPEN_STORE = 2;
    public static final int ON_SELECT_ONE = 3;
    public static final int ON_SELECT_TWO = 4;
    public static final int ON_SELECT_THREE = 5;
    public static final int ON_SELECT_FOUR = 6;

    private Image topBg;
    private ImageButton stageOne, stageTwo, stageThree, stageFour, backButton, storeButton;
    private Group stages;
    private float scale = 3.2f;
    private StoreScreen storeScreen;

    public StageSelectScreen() {
        Image bg = new Image(MainEngine.atlas.findRegion("level_bg"));
        addBackground(bg, true, false);

        storeScreen = new StoreScreen(getWidth(), getHeight());

        topBg = new Image(MainEngine.atlas.findRegion("stage_label"));

        int progress = MainEngine.data.getProgress();


        if ((progress < 40) && !Setting.DEBUG_GAME){
            stageThree = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_three_lock")),
                    new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_three_lock")));
            stageThree.setWidth(getWidth() / scale);
            stageThree.setHeight(getHeight() / scale);
            stageThree.setX(0);
            stageThree.setY(0);
            stageThree.setTouchable(Touchable.disabled);
        }else {
            stageThree = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_three")),
                    new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_three")));


            stageThree.setWidth(getWidth() / scale);
            stageThree.setHeight(getHeight() / scale);
            stageThree.setX(0);
            stageThree.setY(0);

            stageThree.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    call(ON_SELECT_THREE);
                    MainEngine.media.playSound("click.ogg");
                }
            });
        }

        stageOne = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_one")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_one")));

        stageOne.setWidth(getWidth() / scale);
        stageOne.setHeight(getHeight() / scale);
        stageOne.setX(0);
        stageOne.setY(stageThree.getHeight() + 30);


        stageOne.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                call(ON_SELECT_ONE);
                MainEngine.media.playSound("click.ogg");
            }
        });

        if ((progress < 60) && !Setting.DEBUG_GAME){
            stageFour = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_four_lock")),
                    new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_four_lock")));
            stageFour.setWidth(getWidth() / scale);
            stageFour.setHeight(getHeight() / scale);
            stageFour.setX(stageThree.getWidth() + 30);
            stageFour.setY(0);
            stageFour.setTouchable(Touchable.disabled);
        }else {
            stageFour = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_four")),
                    new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_four")));

            stageFour.setWidth(getWidth() / scale);
            stageFour.setHeight(getHeight() / scale);
            stageFour.setX(stageThree.getWidth() + 30);
            stageFour.setY(0);

            stageFour.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    call(ON_SELECT_FOUR);
                    MainEngine.media.playSound("click.ogg");
                }
            });
        }


        if ((progress < 20) && !Setting.DEBUG_GAME){
            stageTwo = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_two_lock")),
                    new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_two_lock")));
            stageTwo.setWidth(getWidth() / scale);
            stageTwo.setHeight(getHeight() / scale);
            stageTwo.setX(stageOne.getWidth() + 30);
            stageTwo.setY(stageFour.getHeight() + 30);
            stageTwo.setTouchable(Touchable.disabled);
        }else {
            stageTwo = new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_two")),
                    new TextureRegionDrawable(MainEngine.atlas.findRegion("bg_stage_two")));

            stageTwo.setWidth(getWidth() / scale);
            stageTwo.setHeight(getHeight() / scale);
            stageTwo.setX(stageOne.getWidth() + 30);
            stageTwo.setY(stageFour.getHeight() + 30);

            stageTwo.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    call(ON_SELECT_TWO);
                    MainEngine.media.playSound("click.ogg");
                }
            });
        }

        stages = new Group();
        stages.addActor(stageOne);
        stages.addActor(stageTwo);
        stages.addActor(stageThree);
        stages.addActor(stageFour);

        stages.setX(((getWidth() - (stageOne.getWidth() * 2) - 30) / 2));
        stages.setY(((getHeight() - (stageOne.getHeight() * 2 + 10) - 80) / 2));

        topBg.setSize(topBg.getWidth(), getHeight() / 4.5f);
        topBg.setX(((getWidth() - ((stageOne.getWidth() + stageTwo.getWidth() + 50))) / 2));
        topBg.setY(stage.getHeight() - topBg.getHeight() - 5);


        addChild(stages);
        addChild(topBg);


        backButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("back_btn")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("back_btn_down")));

        addChild(backButton);

        backButton.setX(20);
        backButton.setY(20);

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainEngine.media.playSound("click.ogg");
                call(ON_BACK);
            }});



        storeButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("store_btn")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("store_btn_down")));

        addChild(storeButton);

        storeButton.setX(getWidth() - storeButton.getWidth() - 20);
        storeButton.setY(20);

        storeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showStoreScreen();
            }
        });

        storeScreen.addListener(new MessageListener(){
            @Override
            protected void receivedMessage(int message, Actor actor) {
                hideStoreScreen();
            }
        });
    }

    private void showStoreScreen() {
        setUntouchButn();
        addOverlayChild(storeScreen);
        topBg.setVisible(false);
        storeScreen.start();
    }

    private void hideStoreScreen() {
        MainEngine.media.playSound("click.ogg");
        topBg.setVisible(true);
        storeScreen.hide();
        delayCall("StoreDestroy", 0.2f);
        setTouchButn();
    }

    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK){
            MainEngine.media.playSound("click.ogg");
            call(ON_BACK);
            return true;
        }
        return super.keyUp(keycode);
    }

    private void setUntouchButn() {
        stageOne.setTouchable(Touchable.disabled);
        stageTwo.setTouchable(Touchable.disabled);
        stageThree.setTouchable(Touchable.disabled);
        stageFour.setTouchable(Touchable.disabled);
        backButton.setTouchable(Touchable.disabled);
        storeButton.setTouchable(Touchable.disabled);
    }

    private void setTouchButn() {
        stageOne.setTouchable(Touchable.enabled);
        stageTwo.setTouchable(Touchable.enabled);
        stageThree.setTouchable(Touchable.enabled);
        stageFour.setTouchable(Touchable.enabled);
        backButton.setTouchable(Touchable.enabled);
        storeButton.setTouchable(Touchable.enabled);
    }

    protected void onDelayCall(String code) {
        if (code.equals("StoreDestroy")) {
            removeOverlayChild(storeScreen);
        }
    }
}
