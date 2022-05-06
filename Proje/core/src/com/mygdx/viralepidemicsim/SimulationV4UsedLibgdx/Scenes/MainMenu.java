package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population.Population;



public class MainMenu implements Screen, ContactListener{

    private GameMain game;
    private Texture bg;
    private Population population;
    private World world;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private Music[] musics;
    private int currentMusic;

    /**
     * Constructor
     * @param game the GameMain object which will store this screen
     */
    public MainMenu(GameMain game){
        this.game = game;
        createMusics();
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM, GameInfo.HEIGHT/GameInfo.PPM);
        box2DCamera.position.set((GameInfo.WIDTH/2f)/GameInfo.PPM , (GameInfo.HEIGHT/2f)/GameInfo.PPM,0);

        debugRenderer = new Box2DDebugRenderer(); 

        world = new World(new Vector2(0,0), true);

        world.setContactListener(this);

        bg = new Texture("BlackBg.png");

        population = new Population(world,500);
        population.getPopulation()[0].makePatientZero();
        box2DCamera.update();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        
        //manually looping the music list
        if(!musics[currentMusic].isPlaying()) {
            if(currentMusic == 4)
                currentMusic = -1;
            musics[++currentMusic].play();
        }

        population.updatePopulation();  
        population.checkBorder();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing the background
        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);

        //Drawing the population one by one
        Person currentPerson;
        for(int i = 0; i < population.getNumberOfPeople(); i++){
            currentPerson = population.getPopulation()[i];

            game.getBatch().draw(currentPerson,(currentPerson.getX() - currentPerson.getWidth()/2), (currentPerson.getY() - currentPerson.getHeight()/2));
        }


        game.getBatch().end();
        
        debugRenderer.render(world, box2DCamera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        box2DCamera.update();

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

    @Override
    public void beginContact(Contact contact) {
        Fixture firstBody = contact.getFixtureA();
        Fixture secondBody = contact.getFixtureB();
        
        String healthCondition1 = ((String) firstBody.getUserData()).substring(0,4);
        
        String healthCondition2 = ((String) secondBody.getUserData()).substring(0,4);


        //If one of the persons that made contact is sick, the other gets a chance to get sick based on their immunity level.
        if(healthCondition2.equals("Sick") && healthCondition1.equals("Heal") && Math.random()*100+1 > population.getPopulation()[Integer.parseInt(((String)firstBody.getUserData()).substring(4))].getImmunity()){
            int healsIndex = Integer.parseInt(((String)firstBody.getUserData()).substring(4)); 
            firstBody.setUserData("Sick" + healsIndex);
            population.getPopulation()[healsIndex].updateHealthCondition();
        }
        else if(healthCondition1.equals("Sick") && healthCondition2.equals("Heal") && Math.random()*100+1 > population.getPopulation()[Integer.parseInt(((String)secondBody.getUserData()).substring(4))].getImmunity()){
            int healsIndex = Integer.parseInt(((String)secondBody.getUserData()).substring(4));
            secondBody.setUserData("Sick" + healsIndex);
            population.getPopulation()[healsIndex].updateHealthCondition();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public World getWorld(){
        return world;
    }

    public void startMusic() {
        musics[currentMusic].play();
    }

    public void pauseMusic() {
        musics[currentMusic].pause();
    }

    //Creating and naming the music objects, and then adding them to musics array
    private void createMusics() {
        currentMusic = 0;
        musics = new Music[5];
        FileHandle src = Gdx.files.internal("music1.mp3");
        musics[0] = Gdx.audio.newMusic(src);
        src = Gdx.files.internal("music2.mp3");
        musics[1] = Gdx.audio.newMusic(src);
        src = Gdx.files.internal("music3.mp3");
        musics[2] = Gdx.audio.newMusic(src);
        src = Gdx.files.internal("music4.mp3");
        musics[3] = Gdx.audio.newMusic(src);
        src = Gdx.files.internal("music5.mp3");
        musics[4] = Gdx.audio.newMusic(src);
    }
}
