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

public class VaccinatedInfo implements Screen{

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
    private BitmapFont font;
    private Texture infoCard;
    private Texture young;
    private Texture youngAdult;
    private Texture old;
    private Texture adult;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public VaccinatedInfo(GameMain main) {
        font = new BitmapFont(Gdx.files.internal("InfoFont3.fnt"), false);
        batch = new SpriteBatch();
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        createButtons();
        addAllListeners();
        Gdx.input.setInputProcessor(stage);
        infoCard = new Texture("InfoCard.png");
        young = new Texture("Young.JPG");
        youngAdult = new Texture("YoungAdult.JPG");
        adult = new Texture("Adult.JPG");
        old = new Texture("Old.JPG");

        stage.addActor(turnBack);
        background = new Texture("BackgroundMain.jpg");

        camera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, 0);
    }

    @Override
    public void show() {
        
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draws the background
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0);
        game.getBatch().end();

        //Draws the names of the buttons on the buttons
        batch.begin();
        batch.draw(infoCard, (GameInfo.WIDTH/2)-418, GameInfo.HEIGHT/2-150);
        font.draw(batch, "Amount of people \nyou have vaccinated", GameInfo.WIDTH/3f, GameInfo.HEIGHT/1.2f);
        batch.draw(young, GameInfo.WIDTH/3f, GameInfo.HEIGHT/1.2f-230);
        font.draw(batch, ": " + Simulation.vaccinatedYoung, GameInfo.WIDTH/3f + 55,  GameInfo.HEIGHT/1.2f-178);
        batch.draw(youngAdult, GameInfo.WIDTH/3f, GameInfo.HEIGHT/1.2f-300);
        font.draw(batch, ": " + Simulation.vaccinatedYoungAdult, GameInfo.WIDTH/3f + 55,  GameInfo.HEIGHT/1.2f-248);
        batch.draw(adult, GameInfo.WIDTH/3f, GameInfo.HEIGHT/1.2f-370);
        font.draw(batch, ": " + Simulation.vaccinatedAdult, GameInfo.WIDTH/3f + 55,  GameInfo.HEIGHT/1.2f-318);
        batch.draw(old, GameInfo.WIDTH/3f, GameInfo.HEIGHT/1.2f-440);
        font.draw(batch, ": " + Simulation.vaccinatedOld, GameInfo.WIDTH/3f + 55,  GameInfo.HEIGHT/1.2f-388);
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
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.gameScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.gameScreen);
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

}
