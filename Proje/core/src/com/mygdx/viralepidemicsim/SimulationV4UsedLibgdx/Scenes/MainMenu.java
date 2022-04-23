package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Value.Fixed;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population.Population;



public class MainMenu implements Screen, ContactListener{

    private GameMain game;

    private Texture bg;

    private Person player;

    private Population population;
    
    private World world;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;



    public MainMenu(GameMain game){
        this.game = game;

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        box2DCamera.position.set((GameInfo.WIDTH/2f) / GameInfo.PPM , (GameInfo.HEIGHT/2f) / GameInfo.PPM,0);

        debugRenderer = new Box2DDebugRenderer(); 

        world = new World(new Vector2(0,0), true);

        world.setContactListener(this);

        bg = new Texture("BlackBg.png");

        population = new Population(world,50);

        population.getPopulation()[0].getSick();

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {

        population.updatePopulation();
        population.checkBorder();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        Person currentPerson;
        for(int i = 0; i < population.getNumberOfPeople(); i++){
            currentPerson = population.getPopulation()[i];

            game.getBatch().draw(currentPerson,(currentPerson.getX() - currentPerson.getWidth()/2), (currentPerson.getY() - currentPerson.getHeight()/2));
        }


        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture firstBody = contact.getFixtureA();
        Fixture secondBody = contact.getFixtureB();


        if(firstBody.getUserData() == ("Healthy") && secondBody.getUserData() == ("Sick")){

        }
        else if(firstBody.getUserData() == ("Sick") && secondBody.getUserData() == ("Healthy")){

        }


    }

    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub
        
    }

    public World getWorld(){
        return world;
    }
    
}
