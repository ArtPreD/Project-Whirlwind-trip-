package ua.com.meraya.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.boontaran.games.StageGame;

import ua.com.meraya.MainEngine;


public class IntroScreen extends StageGame {

    public static final int START = 1;
    Image logo;

    public IntroScreen(){
    //TODO Integrate intro screen in main engine
        logo = new Image(MainEngine.atlas.findRegion("logo"));
        addChild(logo);
        centerActorXY(logo);
        logo.addAction(Actions.alpha(0));

        logo.addAction(Actions.alpha(1, 2f));
        delayCall("hide", 2f);

    }

    @Override
    protected void onDelayCall(String code) {
        if (code.equals("start")){
            call(START);
        }else if (code.equals("hide")){
            logo.addAction(Actions.alpha(0, 1f));
            delayCall("start", 1f);
        }
    }
}

