package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Buttons.OpeningScreenButtons;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class OpeningScreen implements Screen{

    public static Texture background;
    private OrthographicCamera camera;
    private GameMain game;
    private OpeningScreenButtons buttons;
    private SpriteBatch batch;

    public OpeningScreen(GameMain main) {
        game = main;
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

        buttons.getFontNames().draw(batch, "SIMULATION", GameInfo.WIDTH/2f-GameInfo.WIDTH/6.12f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/27f);
        buttons.getFontNames().draw(batch, "GAME", GameInfo.WIDTH/2f+GameInfo.WIDTH/13.5f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/27f);
        buttons.getFontNames().draw(batch, "MAP BUILDER", GameInfo.WIDTH/2f-GameInfo.WIDTH/6f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/7.4f);
        buttons.getFontNames().draw(batch, "HOW TO", GameInfo.WIDTH/2f+GameInfo.WIDTH/16f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/7.4f);
        buttons.getFontNames().draw(batch, "SETTINGS", GameInfo.WIDTH/2f-GameInfo.WIDTH/6.76f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/4.23f);
        buttons.getFontNames().draw(batch, "CREDITS", GameInfo.WIDTH/2f+GameInfo.WIDTH/16f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/4.23f);
        buttons.getFontNames().draw(batch, "EXIT TO DESKTOP", GameInfo.WIDTH/2f-GameInfo.WIDTH/11.6f, GameInfo.HEIGHT/2f -GameInfo.HEIGHT/2.97f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        
    } 

    public OpeningScreenButtons getButtons() {
        return buttons;
    }
}
