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

public class AchiveScreen extends Group {
    public static final int ON_BACK = 1;

    public static boolean enabled = false;

    Image backGround, topBg;
    ImageButton done;

    private float w, h;


    public AchiveScreen(float w, float h) {
        this.w = w;
        this.h = h;

        backGround = new Image(MainEngine.atlas.findRegion("level_list_bg"));
        topBg = new Image(MainEngine.atlas.findRegion("achive_label"));

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
        topBg.addAction(Actions.alpha(0, 0.2f));
    }
}
