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

public class LevelCompletedScreen extends Group {

    public static final int ON_DONE = 1;

    private Image title;
    private ImageButton done;
    private float w, h;

    public LevelCompletedScreen(float w, float h) {
        this.w = w;
        this.h = h;

        title = new Image(MainEngine.atlas.findRegion("level_completed"));
        title.setX((w - title.getWidth()) / 2);
        title.setY(h);
        addActor(title);

        done = new ImageButton(
                new TextureRegionDrawable(MainEngine.atlas.findRegion("done_btn")),
                new TextureRegionDrawable(MainEngine.atlas.findRegion("done_btn_down")) );
        addActor(done);
        done.setY((h - done.getHeight()) / 2 - 60);
        done.setX(w / 2 - done.getWidth() / 2);
        done.setColor(1, 1, 1, 0);

        done.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fire(new MessageEvent(ON_DONE));
                    MainEngine.media.playSound("click.ogg");
            }
        });
    }

    public void start(){
        title.setY(h);
        title.addAction(Actions.moveTo(title.getX(), h - title.getHeight() - 50, 0.5f, Interpolation.swingOut));
        done.addAction(Actions.alpha(1, 0.3f));
    }

}
