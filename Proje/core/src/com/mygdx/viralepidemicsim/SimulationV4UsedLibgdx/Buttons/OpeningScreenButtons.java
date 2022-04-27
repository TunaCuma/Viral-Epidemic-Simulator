package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Buttons;

import javax.xml.namespace.QName;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.CreditsScreen;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.MainMenu;

public class OpeningScreenButtons {
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton simulation;
    private ImageButton gamee;
    private ImageButton howTo;
    private ImageButton credits;
    private ImageButton settings;
    private ImageButton exit;
    private ImageButton mapBuilder;

    public OpeningScreenButtons(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport,game.getBatch());
        createButtons();
        addAllListeners();

        Gdx.input.setInputProcessor(stage);

        stage.addActor(simulation);
        stage.addActor(gamee);
        stage.addActor(howTo);
        stage.addActor(credits);
        stage.addActor(settings);
        stage.addActor(exit);
        stage.addActor(mapBuilder);
    }
    public Stage getStage() {
        return this.stage;
    }

    void createButtons() {
        simulation = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerSimulationButton-modified.png"))));
        gamee = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerGameButton-modified.png"))));
        howTo = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerHowToButton-modified.png"))));
        credits = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerCreditsButton-modified.png"))));
        settings = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerSettingsButton-modified.png"))));
        mapBuilder = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerMapBuilderButton-modified.png"))));
        exit = new ImageButton(new SpriteDrawable(new Sprite(new Texture("BiggerExitButton-modified.png"))));

        simulation.setPosition(GameInfo.WIDTH/2f-200, GameInfo.HEIGHT/2f -60, Align.center);
        gamee.setPosition(GameInfo.WIDTH/2f +200, GameInfo.HEIGHT/2f-60, Align.center);
        howTo.setPosition(GameInfo.WIDTH/2f-200, GameInfo.HEIGHT/2f-160, Align.center);
        credits.setPosition(GameInfo.WIDTH/2f +200, GameInfo.HEIGHT/2f-160, Align.center);
        settings.setPosition(GameInfo.WIDTH/2f-200, GameInfo.HEIGHT/2f-260, Align.center);
        mapBuilder.setPosition(GameInfo.WIDTH/2f +200, GameInfo.HEIGHT/2f-260, Align.center);
        exit.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f-360, Align.center);
    }
    void dispose() {
        simulation.clear();
        gamee.clear();
        howTo.clear();
        credits.clear();
        settings.clear();
        mapBuilder.clear();
        exit.clear();
        stage.dispose();
        gameViewport = null;

                
        game = null;

    }
    void addAllListeners() {
        simulation.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
                dispose();
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
                game.setScreen(new CreditsScreen(game));
                dispose();
            }
        });
    }
}
