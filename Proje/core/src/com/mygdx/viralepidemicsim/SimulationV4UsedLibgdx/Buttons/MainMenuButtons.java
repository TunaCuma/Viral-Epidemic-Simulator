package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class MainMenuButtons {
    
    //instantiative variables
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton simulation;
    private ImageButton howTo;
    private ImageButton credits;
    private ImageButton settings;
    private ImageButton exit;

    private SpriteDrawable template = new SpriteDrawable(new Sprite(new Texture("BiggerMainButton.png")));
    private BitmapFont fontNames;
    public static boolean simInitialized;

    
    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public MainMenuButtons(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        createButtons();
        addAllListeners();
        
        fontNames = new BitmapFont(Gdx.files.internal("ButtonsFont.fnt"));
        Gdx.input.setInputProcessor(stage);

        stage.addActor(simulation);
        stage.addActor(howTo);
        stage.addActor(credits);
        stage.addActor(settings);
        stage.addActor(exit);
    }
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Creating new buttons with their names and positioning them
     */
    void createButtons() {
        simulation = new ImageButton(template);
        howTo = new ImageButton(template);
        credits = new ImageButton(template);
        settings = new ImageButton(template);
        exit = new ImageButton(template);

        simulation.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/20f, Align.center);
        howTo.setPosition(GameInfo.WIDTH/2f-GameInfo.WIDTH/9.6f, GameInfo.HEIGHT/2f-3*GameInfo.HEIGHT/20f, Align.center);
        credits.setPosition(GameInfo.WIDTH/2f +GameInfo.WIDTH/9.6f, GameInfo.HEIGHT/2f-3*GameInfo.HEIGHT/20f, Align.center);
        settings.setPosition(GameInfo.WIDTH/2f-GameInfo.WIDTH/9.6f, GameInfo.HEIGHT/2f-5*GameInfo.HEIGHT/20f, Align.center);
        exit.setPosition(GameInfo.WIDTH/2f +GameInfo.WIDTH/9.6f, GameInfo.HEIGHT/2-5*GameInfo.HEIGHT/20f, Align.center);
    }
    void dispose() {

    }
    /**
     * Adding functionality to the buttons by adding listeners
     */
    void addAllListeners() {
        simulation.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(simInitialized){
                    GameMain.popSound.stop();
                    GameMain.popSound.play();
                    GameMain.beforeScreen = 0;
                    GameMain.stage = (Stage) GameMain.gameScreen.getStage();
                    Gdx.input.setInputProcessor(GameMain.stage);
                    game.setScreen(GameMain.gameScreen);
                }
                else{
                    GameMain.popSound.stop();
                    GameMain.popSound.play();
                    GameMain.beforeScreen = 0;
                    GameMain.stage = (Stage) GameMain.parametersScreen.getStage();
                    Gdx.input.setInputProcessor(GameMain.stage);
                    game.setScreen(GameMain.parametersScreen);
                }
                
            } 
        });
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        credits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.creditsScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.creditsScreen);
            }
        });
        howTo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.howToScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.howToScreen);
            }
        });
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) { 
                GameMain.popSound.stop();
                GameMain.popSound.play();   
                GameMain.beforeScreen = 0;            
                GameMain.stage = (Stage) GameMain.settingsScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.settingsScreen);
            }
        });
    }

    public BitmapFont getFontNames() {
        return fontNames;
    }
}
