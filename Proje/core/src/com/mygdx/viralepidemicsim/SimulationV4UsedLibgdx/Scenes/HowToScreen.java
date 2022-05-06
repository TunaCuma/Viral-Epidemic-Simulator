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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class HowToScreen implements Screen{

    public static Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private String[] HowToImages;
    private ImageButton[] slides;
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public HowToScreen(GameMain main) {
        batch = new SpriteBatch();
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        HowToImages = new String[] {"howto1.png","howto2.png"};
        slides = createSlides();
        addAllListeners();
        Gdx.input.setInputProcessor(stage);

        for(ImageButton button: slides){
            stage.addActor(button);
        }

        background = new Texture("BackgroundMain.jpg");

        camera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, 0);


    }
    public ImageButton[] createSlides(){
        ImageButton[] slides = new ImageButton[HowToImages.length];
        int index = 0;
        for(String file: HowToImages){
            slides[index] = new ImageButton(new SpriteDrawable(new Sprite(new Texture(file))));
            slides[index].setPosition(0, 0, Align.bottomLeft);

            index++;
        }
        return slides;
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
        
        for(int i=0; i<slides.length; i++){
            if(i<slides.length-1){
                slides[i].addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        
                    }
                });
            }
            else{
                slides[i].addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        GameMain.stage = GameMain.openingScreen.getButtons().getStage();
                        game.setScreen(GameMain.openingScreen);
                        Gdx.input.setInputProcessor(GameMain.openingScreen.getButtons().getStage());
                    }
                });
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

}
