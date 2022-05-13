package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class CreditsScreen implements Screen{

    public static Texture background;
    private SpriteBatch batch;
    private ImageButton turnBack;
    private OrthographicCamera camera;
    private BitmapFont fontCredits;
    private BitmapFont fontNames;
    private String creditsString;
    private String faik, tarik, emre, tuna, gun;
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public CreditsScreen(GameMain main) {
        batch = new SpriteBatch();
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        createButtons();
        addAllListeners();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(turnBack);
        background = new Texture("BackgroundMain.jpg");

        camera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, 0);
        fontCredits = new BitmapFont(Gdx.files.internal("CreditsFont.fnt"));
        fontNames = new BitmapFont(Gdx.files.internal("NamesFont.fnt"));
        creditsString = "CREDITS";
        faik = "AHMET FAIK UTKU"; tarik = "AHMET TARIK UCUR"; tuna = "TUNA CUMA"; emre = "EMRE AKGUL"; gun = "GUN TASTAN";

    }

    @Override
    public void show() {
        
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Simulation.musicPlayer();
        //Draws the background
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0);
        game.getBatch().end();

        //Draws the names of the buttons on the buttons
        batch.begin();
        fontCredits.draw(batch, creditsString, GameInfo.WIDTH/3f, GameInfo.HEIGHT/1.2f);
        fontNames.draw(batch, faik, GameInfo.WIDTH/2.6f, GameInfo.HEIGHT/2.4f+5*GameInfo.HEIGHT/20);
        fontNames.draw(batch, tarik, GameInfo.WIDTH/2.7f, GameInfo.HEIGHT/2.4f+4*GameInfo.HEIGHT/20);
        fontNames.draw(batch, emre, GameInfo.WIDTH/2.37f, GameInfo.HEIGHT/2.4f+3*GameInfo.HEIGHT/20);
        fontNames.draw(batch, gun, GameInfo.WIDTH/2.4f, GameInfo.HEIGHT/2.4f+2*GameInfo.HEIGHT/20);
        fontNames.draw(batch, tuna, GameInfo.WIDTH/2.36f, GameInfo.HEIGHT/2.4f+GameInfo.HEIGHT/20);
        batch.end();

        //Draws the stage and the buttons in it
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
    } 

    /**
     * Creates the buttons required to return to the OpeningScreen
     */
    void createButtons() {
        turnBack = new ImageButton(new SpriteDrawable(new Sprite(new Texture("TurnBack.png") )));
        turnBack.setPosition(170, GameInfo.HEIGHT*2/2f-60, Align.center);
    }

    /**
     * Adds functionality to the button
     */
    void addAllListeners() {
        turnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.stage = GameMain.openingScreen.getButtons().getStage();
                game.setScreen(GameMain.openingScreen);
                Gdx.input.setInputProcessor(GameMain.openingScreen.getButtons().getStage());
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

}
