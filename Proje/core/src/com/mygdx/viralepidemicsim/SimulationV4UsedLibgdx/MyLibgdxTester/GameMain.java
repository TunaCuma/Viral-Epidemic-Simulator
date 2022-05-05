package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.*;

public class GameMain extends Game{

    public SpriteBatch batch;
    public OpeningScreen openingScreen;

    @Override
    public void create() {
        //Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        batch = new SpriteBatch();
        GameInfo.create();
        openingScreen = new OpeningScreen(this);
        //setScreen(openingScreen);
        setScreen(new MainMenu(this));
    }

    public void render() {
        super.render();

    }

    public SpriteBatch getBatch(){
        return this.batch;
    }
    
}
