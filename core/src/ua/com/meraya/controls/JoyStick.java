package ua.com.meraya.controls;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class JoyStick extends Group {

    private Image idle, right, left;

    private static final int IDLE = 0;
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private int direction;

    public JoyStick(float minHeight){
        idle = new Image(MainEngine.atlas.findRegion("joystick"));
        addActor(idle);

        float scale = 1;

        if (idle.getHeight() < minHeight){
            scale = minHeight / idle.getHeight();
        }
        idle.setHeight(idle.getHeight() * scale);
        idle.setWidth(idle.getWidth() * scale);

        setSize(idle.getWidth(), idle.getHeight());

        right = new Image(MainEngine.atlas.findRegion("joystick_right"));
        right.setSize(getWidth(), getHeight());
        addActor(right);

        left = new Image(MainEngine.atlas.findRegion("joystick_left"));
        left.setSize(getWidth(), getHeight());
        addActor(left);

        setDirection(IDLE);

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                handleTouch(x, y);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                handleTouch(x, y);
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setDirection(IDLE);
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    public boolean isRight(){
        return direction == RIGHT;
    }

    public boolean isLeft(){
        return direction == LEFT;
    }

    private void handleTouch(float x, float y){
        if (x > getWidth() / 2){
            setDirection(RIGHT);
        }else {
            setDirection(LEFT);
        }
    }

    private void setDirection(int direction){
        idle.setVisible(false);
        right.setVisible(false);
        left.setVisible(false);

        switch (direction){
            case IDLE:
                idle.setVisible(true);
                break;
            case RIGHT:
                right.setVisible(true);
                break;
            case LEFT:
                left.setVisible(true);
                break;
        }

        this.direction = direction;
    }
}
