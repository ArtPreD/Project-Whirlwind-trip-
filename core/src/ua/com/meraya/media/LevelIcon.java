package ua.com.meraya.media;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ua.com.meraya.MainEngine;


public class LevelIcon extends Group{
    private int id;
    private Label label;
    private Image lockImg, bg, bgDown, hiliteImg;
    private boolean isHilited = false;
    private boolean alphaUp = false;

    public LevelIcon(int id) {
        this.id = id;
        hiliteImg = new Image(MainEngine.atlas.findRegion("level_icon_hilite"));
        addActor(hiliteImg);
        hiliteImg.setVisible(false);

        bg = new Image(MainEngine.atlas.findRegion("level_icon_bg"));
        bg.setSize(64, 64);
        addActor(bg);
        setSize(bg.getWidth(), bg.getHeight());

        hiliteImg.setSize(64, 64);
        hiliteImg.setX((getWidth() - hiliteImg.getWidth()) / 2);
        hiliteImg.setY((getHeight() - hiliteImg.getHeight()) / 2);

        bgDown = new Image(MainEngine.atlas.findRegion("level_icon_bg_down"));
        bgDown.setSize(64, 64);
        addActor(bgDown);
        bgDown.setX(bg.getX() + (bg.getWidth() - bgDown.getWidth()) / 2);
        bgDown.setY(bg.getY() + (bg.getHeight() - bgDown.getHeight()) / 2);
        bgDown.setVisible(false);

        lockImg = new Image(MainEngine.atlas.findRegion("level_icon_lock"));
        lockImg.setSize(64,64);
        lockImg.setX((getWidth() - lockImg.getWidth()) / 2);
        lockImg.setY((getHeight() - lockImg.getHeight()) / 2);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = MainEngine.font40;
        style.fontColor = new Color(0x000000ff);

        label = new Label(id + "", style);
        //label.setSize(32, 32);
        label.setFontScale(0.7f);
        label.setX((getWidth() - label.getWidth()) / 2 + 8);
        label.setY((getHeight() - label.getHeight()) / 2 + 5);

        setLock(true);

        addCaptureListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                event.setTarget(LevelIcon.this);
                return true;
            }
        });

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bgDown.setVisible(true);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                bgDown.setVisible(false);
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    public int getId(){
        return id;
    }

    public void setLock(boolean lock){
        if(lock){
            label.remove();
            addActor(lockImg);
            setTouchable(Touchable.disabled);
        }else {
            lockImg.remove();
            addActor(label);
            setTouchable(Touchable.enabled);
        }
    }

    public void setHilite(){
        hiliteImg.setVisible(true);
        isHilited = true;
    }

    @Override
    public void act(float delta) {
        if (isHilited) {
            float alpha = hiliteImg.getColor().a;
            float alpha2 = bg.getColor().a;

            if (alphaUp) {
                alpha += delta * 2;
                alpha2 -= delta *2;
                if (alpha >= 1) {
                    alpha = 1;
                    alpha2 = 0;
                    alphaUp = false;
                }
            } else {
                alpha -= delta * 2;
                alpha2 += delta *2;
                if (alpha < 0) {
                    alpha = 0;
                    alpha2 = 1;
                    alphaUp = true;
                }
            }
            hiliteImg.setColor(1, 1, 1, alpha);
            bg.setColor(1, 1, 1, alpha2);
        }
        super.act(delta);
    }
}
