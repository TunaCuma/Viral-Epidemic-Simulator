package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class HowToScreen implements Screen{

    //static variable
    public static Texture background;

    //instantiative variables
    private ImageButton turnBack;
    private ImageButton forward;
    private OrthographicCamera camera;
    private String[] HowToImages;
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private int currentBackgroundIndex;
    private Texture[] textures;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public HowToScreen(GameMain main) {
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        HowToImages = new String[] {"howto1.png","howto2.png","howto3.png"};
        textures = new Texture[HowToImages.length];
        createTextures();
        
        Gdx.input.setInputProcessor(stage);
        
        currentBackgroundIndex = 0;
        background = new Texture(HowToImages[currentBackgroundIndex]); //initilazing first background

        //buttons
        createButtons();
        addAllListeners();
        stage.addActor(turnBack);
        stage.addActor(forward);

        camera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, 0);


    }
    /**
     * creates button
     */
    void createButtons() {
        turnBack = new ImageButton(new SpriteDrawable(new Sprite(new Texture("TurnBack.png") )));
        turnBack.setPosition(170, 60, Align.center);
        forward = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Forward.png") )));
        forward.setPosition(GameInfo.WIDTH-170, 60, Align.center);
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
     * Adds functionality to the button
     */
    void addAllListeners() {
        turnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                previousSlide();
            }
        });
        forward.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextSlide();
            }
        });
    }

    public void nextSlide(){
        GameMain.popSound.stop();
        GameMain.popSound.play();
        if(currentBackgroundIndex+1!=HowToImages.length){
            currentBackgroundIndex++;
            background = textures[currentBackgroundIndex];
        } //If we are not at last index
        else{
            currentBackgroundIndex=0;
            background = textures[currentBackgroundIndex];
            GameMain.stage = GameMain.openingScreen.getButtons().getStage();
            game.setScreen(GameMain.openingScreen);
            Gdx.input.setInputProcessor(GameMain.openingScreen.getButtons().getStage());
        } //turn to opening screen 
    }

    public void previousSlide(){
        GameMain.popSound.stop();
        GameMain.popSound.play();
        if(currentBackgroundIndex!=0){
            currentBackgroundIndex--;
            background = textures[currentBackgroundIndex];
        } //If we are not at first index
        else{
            currentBackgroundIndex=0;
            background = textures[currentBackgroundIndex];
            GameMain.stage = GameMain.openingScreen.getButtons().getStage();
            game.setScreen(GameMain.openingScreen);
            Gdx.input.setInputProcessor(GameMain.openingScreen.getButtons().getStage());
        } //turn to opening screen 
    }
    public Stage getStage() {
        return stage;
    }

    private void createTextures() {
        for(int i = 0; i < textures.length; i++) 
            textures[i] = new Texture(HowToImages[i]);
    }

}
