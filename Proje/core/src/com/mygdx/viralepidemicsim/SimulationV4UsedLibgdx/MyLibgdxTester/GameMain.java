package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester;

import java.io.File;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.CreditsScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.HowToScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.OpeningScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Settings;

public class GameMain extends Game{

    public SpriteBatch batch;
    public static OpeningScreen openingScreen; // 0
    public static CreditsScreen creditsScreen; // 1
    public static HowToScreen howToScreen; // 2
    public static Simulation gameScreen; // 3
    public static Settings settingsScreen; // 4
    public static Stage stage;
    public static Music popSound;
    public static int beforeScreen;

    @Override
    public void create() {
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
        creditsScreen = new CreditsScreen(this);
        gameScreen = new Simulation(this);
        settingsScreen = new Settings(this);
        howToScreen = new HowToScreen(this);
        openingScreen = new OpeningScreen(this);
        setScreen(openingScreen);
    }
}