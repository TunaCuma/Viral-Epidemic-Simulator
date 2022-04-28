package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Buttons.OpeningScreenButtons;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

public class CreditsScreen implements Screen{

    public static Texture background;
    public static Texture credits;
    private ImageButton turnBack;
    private OrthographicCamera camera;
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    public CreditsScreen(GameMain main) {
        game = main;
        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        createButtons();
        addAllListeners();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(turnBack);
        background = new Texture("BackgroundMain.jpg");
        credits = new Texture("Credits_prev_ui.png");
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
        game.getBatch().draw(credits, GameInfo.WIDTH/2f-GameInfo.WIDTH/2, GameInfo.HEIGHT/2f-GameInfo.HEIGHT/2, GameInfo.WIDTH, GameInfo.HEIGHT);
        game.getBatch().end();
        stage.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        background.dispose();
        credits.dispose();
        turnBack.clear();
        camera = null;
        game = null;
    } 

    void createButtons() {
        turnBack = new ImageButton(new SpriteDrawable(new Sprite(new Texture("TurnBack.png") )));
        turnBack.setPosition(170, GameInfo.HEIGHT*2/2f-60, Align.center);
    }

    void addAllListeners() {
        turnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new OpeningScreen(game));
                dispose();
            }
        });
    }

}
