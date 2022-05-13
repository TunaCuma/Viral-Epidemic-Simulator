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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class Settings implements Screen{

    public static Texture background;
    private ImageButton turnBack;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private BitmapFont musicVolume;
    private BitmapFont sfxVolume;

    Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
    private Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
    private Slider sfxSlide = new Slider( 0f, 1f, 0.1f,false, skin );
    
    
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public Settings(GameMain main) {
        //nukeSound.play();

        batch = new SpriteBatch();
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        createButtons();       
        addAllListeners();
        Gdx.input.setInputProcessor(stage);

        
        volumeMusicSlider.setPosition(650, 550);
        volumeMusicSlider.setWidth(600);

        sfxSlide.setPosition(650, 200);
        sfxSlide.setWidth(600);

        sfxSlide.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.setVolume(sfxSlide.getValue());
                Simulation.newDaySound.setVolume(sfxSlide.getValue());
            }
            
        });
        volumeMusicSlider.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for(int i = 0; i < Simulation.musics.length; i++)
                    Simulation.musics[i].setVolume(volumeMusicSlider.getValue());
            }
        });
        

        volumeMusicSlider.setValue(1f);
        sfxSlide.setValue(1f);
        stage.addActor(volumeMusicSlider);;
        stage.addActor(sfxSlide);

        stage.addActor(turnBack);
        background = new Texture("BackgroundMain.jpg");

        font = new BitmapFont(Gdx.files.internal("CreditsFont.fnt"), false);
        musicVolume = new BitmapFont(Gdx.files.internal("NamesFont.fnt")); 
        sfxVolume = new BitmapFont(Gdx.files.internal("NamesFont.fnt")); 

        camera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, 0);


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
        font.draw(batch, "SETTINGS", GameInfo.WIDTH/3.4f, GameInfo.HEIGHT/1.03f);
        musicVolume.draw(batch, "Music Volume", GameInfo.WIDTH/2.4f, GameInfo.HEIGHT/2f+4*GameInfo.HEIGHT/20);
        sfxVolume.draw(batch, "SFX Volume", GameInfo.WIDTH/2.35f, GameInfo.HEIGHT/3.5f+2*GameInfo.HEIGHT/20);
        
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
    }
    void createButtons() {
        turnBack = new ImageButton(new SpriteDrawable(new Sprite(new Texture("TurnBack.png") )));
        turnBack.setPosition(170, GameInfo.HEIGHT*2/2f-60, Align.center);
    }
    

    public Stage getStage() {
        return stage;
    }

}
