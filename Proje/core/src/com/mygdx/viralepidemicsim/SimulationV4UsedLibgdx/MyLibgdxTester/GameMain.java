package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.CreditsScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.MainMenu;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.OpeningScreen;

public class GameMain extends Game{

    public SpriteBatch batch;
    public static OpeningScreen openingScreen;
    public static CreditsScreen creditsScreen;
    public static MainMenu gameScreen;
    public static Stage stage;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        batch = new SpriteBatch();
        GameInfo.create();
        createScreens();
        setScreen(openingScreen);
        //setScreen(new MainMenu(this));
    }

    public void render() {
        super.render();
    }

    public SpriteBatch getBatch(){
        return this.batch;
    }

    void createScreens() {
        creditsScreen = new CreditsScreen(this);
        gameScreen = new MainMenu(this);
        openingScreen = new OpeningScreen(this);
    }
}
