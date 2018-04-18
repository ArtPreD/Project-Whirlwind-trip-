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


public class LevelListTwoScreen extends StageGame {
    public static final int ON_BACK = 1;
    public static final int ON_LEVEL_SELECTED = 2;
    public static final int ON_OPEN_MARKET = 3;
    public static final int ON_SHARE = 4;

    private Group container;
    private int selectedLevelId = 0;

    public LevelListTwoScreen() {
        Image bg = new Image(MainEngine.atlas.findRegion("level_bg"));
        addBackground(bg, true, false);

        Image iconBg = new Image(MainEngine.atlas.findRegion("level_list_bg"));
        iconBg.setSize((getWidth() / 2) + 130, (getHeight() / 2) + 150);
        iconBg.setPosition(21,(getHeight() / 2) - 200);
        addChild(iconBg);

        Image iconTopBg = new Image(MainEngine.atlas.findRegion("level_one_label"));
        iconTopBg.setSize((getWidth() / 2) + 120,(getHeight() / 2) - 140);
        iconTopBg.setPosition(25, (getHeight() / 2) + 135);
        addChild(iconTopBg);




        container = new Group();
        addChild(container);

        int row = 4, col = 5;
        float space = 25;

        float iconWidth = 0, iconHeight = 0;
        int id = 21;
        int x, y;

        int progress = MainEngine.data.getProgress();

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
                //if (id == progress){
                //      icon.setHilite();
                //  }
                if (Setting.DEBUG_GAME){
                    icon.setLock(false);
                }

                icon.addListener(iconListener);
                id++;
            }
        }
        container.setWidth(col * iconWidth + (col - 1) * space);
        container.setHeight(row * iconHeight + (row - 1) * space);

        container.setX(30);
        container.setY(getHeight() - container.getHeight() - 30);

        container.setColor(1, 1, 1, 0);
        container.addAction(Actions.alpha(1, 0.4f));

        ImageButton rateButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("rate")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("rate_down")));

        addChild(rateButton);
        rateButton.setX(getWidth() - rateButton.getWidth() - 20);
        rateButton.setY(getHeight() - rateButton.getHeight() - 20);

        rateButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                call(ON_OPEN_MARKET);
            }
        });

        ImageButton shareButton =
                new ImageButton(new TextureRegionDrawable(MainEngine.atlas.findRegion("share")),
                        new TextureRegionDrawable(MainEngine.atlas.findRegion("share_down")));

        addChild(shareButton);
        shareButton.setX(getWidth() - shareButton.getWidth() - 20);
        shareButton.setY(20);

        shareButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                call(ON_SHARE);
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
