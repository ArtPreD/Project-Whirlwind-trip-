package ua.com.meraya.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boontaran.games.StageGame;

import ua.com.meraya.MainEngine;
import ua.com.meraya.Setting;
import ua.com.meraya.media.LevelIcon;


public class LevelListScreen extends StageGame {
    public static final int ON_BACK = 1;
    public static final int ON_LEVEL_SELECTED = 2;
   // public static final int ON_OPEN_MARKET = 3;
    //public static final int ON_SHARE = 6;
    public static final int ON_OPEN_STORE = 3;

    private Group container;
    private Image iconBg, iconTopBg;
    private int selectedLevelId = 0;

    public LevelListScreen() {
        Image bg = new Image(MainEngine.atlas.findRegion("level_bg"));
        addBackground(bg, true, false);

        iconBg = new Image(MainEngine.atlas.findRegion("level_list_bg"));
       // iconBg.setSize((getWidth() / 2) + 130, (getHeight() / 2) + 150);
      //  iconBg.setPosition(21,(getHeight() / 2) - 200);

        iconTopBg = new Image(MainEngine.atlas.findRegion("level_one_label"));
       // iconTopBg.setSize((getWidth() / 2) + 120,(getHeight() / 2) - 140);
       // iconTopBg.setPosition(25, (getHeight() / 2) + 135);

        container = new Group();


        addChild(iconBg);
        addChild(iconTopBg);
        addChild(container);

        int row = 4, col = 5;
        float space = 25;

        float iconWidth = 0, iconHeight = 0;
        int id = 1;
        int x, y;

        int progress =  7; //MainEngine.data.getProgress();

        for (y = 0; y < row; y++){
            for (x = 0; x < col; x++){
                LevelIcon icon = new LevelIcon(id);
                container.addActor(icon);

                if (iconWidth == 0){
                    iconWidth = icon.getWidth();
                    iconHeight = icon.getHeight();
                }

                icon.setX(x * (iconWidth + space));
                icon.setY(((row - 1) - y) * iconHeight + space - 15);

                if (id <= progress){
                    icon.setLock(false);
                }
                if (id == progress){
                    icon.setHilite();
                }
                if (Setting.DEBUG_GAME){
                    icon.setLock(false);
                }

                icon.addListener(iconListener);
                id++;
            }
        }
        container.setWidth(col * iconWidth + (col - 1) * space);
        container.setHeight(row * iconHeight + (row - 1) * space);

        container.setX((getWidth() - container.getWidth()) / 2 - 10);
        container.setY((getHeight() - container.getHeight()) / 2 + 20);

        container.setColor(1, 1, 1, 0);
        container.addAction(Actions.alpha(1, 0.4f));

        iconBg.setWidth((container.getWidth()) + 130);
        iconBg.setHeight(container.getHeight() + 70);
        iconBg.setX((getWidth() - iconBg.getWidth()) / 2);
        iconBg.setY((getHeight() - iconBg.getHeight()) / 2 - 20);

        iconTopBg.setX((container.getX()));
        iconTopBg.setY(container.getHeight() + 40);
        iconTopBg.setWidth(container.getWidth() + 25);
        iconTopBg.setHeight((getHeight() / 2) - 140);

        ImageButton backButton =
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

        ImageButton storeButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("store_btn")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("store_btn_down")));

        addChild(storeButton);

        storeButton.setX(getWidth() - storeButton.getWidth() - 20);
        storeButton.setY(20);

        storeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainEngine.media.playSound("click.ogg");
                call(ON_OPEN_STORE);
            }
        });
    }

    public int getSelectedLevelId(){
        return selectedLevelId;
    }

    private ClickListener iconListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            selectedLevelId = ((LevelIcon)event.getTarget()).getId();
            MainEngine.media.playSound("click.ogg");
             call(ON_LEVEL_SELECTED);
        }
    };

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK){
            MainEngine.media.playSound("click.ogg");
            call(ON_BACK);
            return true;
        }

        return super.keyUp(keycode);
    }
}
