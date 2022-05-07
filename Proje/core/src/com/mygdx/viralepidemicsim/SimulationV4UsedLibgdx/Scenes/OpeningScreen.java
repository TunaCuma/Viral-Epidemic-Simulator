package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Buttons.OpeningScreenButtons;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class OpeningScreen implements Screen{

    private BitmapFont font;
    public static Texture background;
    private OrthographicCamera camera;
    private GameMain game;
    private OpeningScreenButtons buttons;
    private SpriteBatch batch;

    /**
     * Constructor
     * @param main the GameMain object which will store this screen
     */
    public OpeningScreen(GameMain main) {
        game = main;
        font = new BitmapFont(Gdx.files.internal("TitleFont.fnt"));
        buttons = new OpeningScreenButtons(game);
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        batch = new SpriteBatch();

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

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0);
        game.getBatch().end();
        game.getBatch().setProjectionMatrix(buttons.getStage().getCamera().combined);
        buttons.getStage().draw();
        batch.begin();
        
        font.draw(batch, "VIRAL EPIDEMIC\n   SIMULATOR", GameInfo.WIDTH/3.7f, GameInfo.HEIGHT/1.2f);
        
        //Drawing the names for the buttons
        buttons.getFontNames().draw(batch, "SIMULATION  ", GameInfo.WIDTH/2f-GameInfo.WIDTH/6.12f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/27f);
        buttons.getFontNames().draw(batch, "           GAME", GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/27f);
        buttons.getFontNames().draw(batch, "   HOW TO    ", GameInfo.WIDTH/2f-GameInfo.WIDTH/6.12f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/7.4f);
        buttons.getFontNames().draw(batch, "         CREDITS", GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/7.4f);
        buttons.getFontNames().draw(batch, "  SETTINGS   ", GameInfo.WIDTH/2f-GameInfo.WIDTH/6.12f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/4.23f);
        buttons.getFontNames().draw(batch, "  EXIT TO DESKTOP", GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/4.23f);
        batch.end();
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

    public OpeningScreenButtons getButtons() {
        return buttons;
    }
}
