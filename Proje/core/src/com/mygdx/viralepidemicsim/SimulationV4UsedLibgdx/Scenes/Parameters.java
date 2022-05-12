package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class Parameters implements Screen{

    public static Texture background;
    private ImageButton turnBack;
    private SpriteDrawable startUp;
    private SpriteDrawable startDown;
    private ImageButton start;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private BitmapFont smallFont;
    private Dialog dialog;
    private ImageButton curfew;
    private SpriteDrawable curfewUp;
    private SpriteDrawable curfewDown; 

    Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

    public Slider spreadRate = new Slider( 0f, 1f, 0.01f,false, skin );
    public Slider population = new Slider( 0f, 500f, 1f,false, skin );
    public Slider vaccination = new Slider( 0f, 1f, 0.01f,false, skin );
    public Slider killRate = new Slider( 0f, 1f, 0.01f,false, skin );
    public Slider patientNumber = new Slider( 0f, 500f, 1f,false, skin );
    public final SelectBox<String> selectBox = new SelectBox<String>(skin);

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public Parameters(GameMain main) {
        selectBox.setItems("Influenza","SARS-CoV-2","Rabies");
        batch = new SpriteBatch();
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        createButtons();       
        addAllListeners();
        Gdx.input.setInputProcessor(stage);
        
        selectBox.setSize(300, population.getHeight());
        selectBox.setPosition(1220,440);

        population.setPosition(1220, 820);
        population.setWidth(490);

        patientNumber.setPosition(1220, 630);
        patientNumber.setWidth(490);

        spreadRate.setPosition(245, 820);
        spreadRate.setWidth(490);

        killRate.setPosition(245, 630);
        killRate.setWidth(490);

        vaccination.setPosition(245, 440);
        vaccination.setWidth(490);

        curfew.setPosition(245, 250);


        

        stage.addActor(selectBox);
        stage.addActor(killRate);
        stage.addActor(spreadRate);
        stage.addActor(population);
        stage.addActor(patientNumber);
        stage.addActor(vaccination);
        stage.addActor(curfew);

        stage.addActor(turnBack);
        stage.addActor(start);
        background = new Texture("BackgroundMain.jpg");

        font = new BitmapFont(Gdx.files.internal("CreditsFont.fnt"), false);
        smallFont = new BitmapFont(Gdx.files.internal("NamesFont.fnt")); 
        smallFont.getData().setScale(0.7f, 0.7f);
        camera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, 0);


    }    

    @Override
    public void show() {
        
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draws the background
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0);
        game.getBatch().end();

        if(patientNumber.getValue()>population.getValue()){
            patientNumber.setValue(population.getValue());
        }
        
        //Draws the names of the buttons on the buttons
        batch.begin();
        smallFont.draw(batch,"" + (int) population.getValue(), population.getX() + population.getWidth() + 15, population.getY() +(population.getHeight()/2) +15);
        smallFont.draw(batch,"" + (int) patientNumber.getValue(), patientNumber.getX() + patientNumber.getWidth() + 15, patientNumber.getY() +(patientNumber.getHeight()/2) +15);
        smallFont.draw(batch,"%" + (int) (killRate.getValue() * 100), killRate.getX() + killRate.getWidth() + 15, killRate.getY() +(killRate.getHeight()/2) +15);
        smallFont.draw(batch,"%" + (int) (spreadRate.getValue() * 100), spreadRate.getX() + spreadRate.getWidth() + 15, spreadRate.getY() +(spreadRate.getHeight()/2) +15);
        smallFont.draw(batch,"%" + (int) (vaccination.getValue() * 100), vaccination.getX() + vaccination.getWidth() + 15, vaccination.getY() +(vaccination.getHeight()/2) +15);

        smallFont.draw(batch, "Population", population.getX() + 15, population.getY() + population.getHeight() +40);
        smallFont.draw(batch, "Initial Patient Number", patientNumber.getX() + 15, patientNumber.getY() + patientNumber.getHeight() +40);
        smallFont.draw(batch, "Rate of Spread", spreadRate.getX() + 15, spreadRate.getY() + spreadRate.getHeight() +40);
        smallFont.draw(batch, "Rate of Kill", killRate.getX() + 15, killRate.getY() + killRate.getHeight() +40);
        smallFont.draw(batch, "Vaccination Rate", vaccination.getX() + 15, vaccination.getY() + vaccination.getHeight() +40);
        smallFont.draw(batch, "Select From Known Viruses", selectBox.getX() + 15, selectBox.getY() + selectBox.getHeight() +40);


        batch.end();
        
        

        
        //Draws the stage and the buttons in it
        stage.act();
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

    }

    @Override
    public void dispose() {
        
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
                if(GameMain.beforeScreen == 3) {
                    GameMain.stage = GameMain.gameScreen.getStage();
                    Gdx.input.setInputProcessor(GameMain.stage);
                    game.setScreen(GameMain.gameScreen);
                }
                else {
                    GameMain.stage = GameMain.openingScreen.getButtons().getStage();
                    Gdx.input.setInputProcessor(GameMain.openingScreen.getButtons().getStage());
                    game.setScreen(GameMain.openingScreen);
                }
            }
        });

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.gameScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.gameScreen);

                GameInfo.setRateOfKill(killRate.getValue());
                GameInfo.setRateOfSpread(spreadRate.getValue());
                GameInfo.setPopulation((int)population.getValue());
                


                GameMain.gameScreen.population.randomInfection((int)patientNumber.getValue());
                GameMain.gameScreen.population.randomVaccination((int)(GameInfo.population * vaccination.getValue()));



            }
        });
        curfew.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 2;
                GameMain.stage = (Stage) GameMain.curfewScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.curfewScreen);
            }
        });
        
        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                if(selectBox.getSelected().equals("SARS-CoV-2")){
                    killRate.setValue(3f);
                    spreadRate.setValue(2f);
                }
            }
        });
        
    }
    void createButtons() {
        curfewUp = new SpriteDrawable(new Sprite(new Texture("CurfewButton2.png") ));
        curfewDown = new SpriteDrawable(new Sprite(new Texture("CurfewButtonPressed2.png") ));
        curfew = new ImageButton(curfewUp, curfewDown);
        startUp = new SpriteDrawable(new Sprite(new Texture("StartButton.png")));
        startDown = new SpriteDrawable(new Sprite(new Texture("StartButtonPressed.png")));
        start = new ImageButton(startUp,startDown);
        start.setPosition((GameInfo.WIDTH/2)-(start.getWidth()/2), 40);
        turnBack = new ImageButton(new SpriteDrawable(new Sprite(new Texture("TurnBack.png") )));
        turnBack.setPosition(170, GameInfo.HEIGHT*2/2f-60, Align.center);
    }
    

    public Stage getStage() {
        return stage;
    }

}
