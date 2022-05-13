package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.CreditsScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.CurfewScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.HowToScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.VaccinatedInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.MainMenu;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Parameters;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Settings;

public class GameMain extends Game{

    public SpriteBatch batch;
    public static MainMenu openingScreen; // 0
    public static CreditsScreen creditsScreen; // 1
    public static HowToScreen howToScreen; // 2
    public static Simulation gameScreen; // 3
    public static Settings settingsScreen; // 4
    public static CurfewScreen curfewScreen;
    public static Parameters parametersScreen;
    public static VaccinatedInfo vaccinated;
    public static Stage stage;
    public static Music popSound;
    public static int beforeScreen;

    @Override
    public void create() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));
        Cursor cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(cursor);
        pixmap.dispose();
        popSound = Gdx.audio.newMusic(Gdx.files.internal("popSound.mp3"));
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        batch = new SpriteBatch();
        GameInfo.create();
        createScreens();
        gameScreen.startMusic();
    }

    public void render() {
        super.render();
    }

    public SpriteBatch getBatch(){
        return this.batch;
    }

    void createScreens() {
        curfewScreen = new CurfewScreen(this);
        creditsScreen = new CreditsScreen(this);
        gameScreen = new Simulation(this);
        vaccinated = new VaccinatedInfo(this);
        settingsScreen = new Settings(this);
        parametersScreen = new Parameters(this);
        howToScreen = new HowToScreen(this);
        openingScreen = new MainMenu(this);
        setScreen(openingScreen);
    }
}