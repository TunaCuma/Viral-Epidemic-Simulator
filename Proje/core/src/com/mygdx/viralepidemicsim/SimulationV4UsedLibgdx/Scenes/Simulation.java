package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.GraphPlotter.java.GraphPlotter;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population.Population;


public class Simulation implements Screen, ContactListener{

    private BitmapFont font, newDayFont;
    private GameMain game;
    private Texture bg;
    private Texture gui;
    public static Population population;
    private World world;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    protected static Music[] musics;
    private int currentMusic;
    private Stage stage;
    private Viewport viewport;
    private ImageButton settings;
    private ImageButton mask;
    private SpriteDrawable maskUp;
    private SpriteDrawable maskDown;
    private ImageButton curfew;
    private SpriteDrawable curfewUp;
    private SpriteDrawable curfewDown; 
    private ImageButton turnBack;
    private ImageButton vaccButton;
    private SpriteDrawable vaccImage;
    private SpriteDrawable vaccImageDown;
    private ImageButton analyzeButton;
    private SpriteDrawable analyzeImage;
    private SpriteDrawable analyzeImageDown;
    private SpriteDrawable adult;
    private SpriteDrawable adultDown;
    private SpriteDrawable young;
    private SpriteDrawable youngDown;
    private SpriteDrawable youngadult;
    private SpriteDrawable youngadultDown;
    private SpriteDrawable old;
    private SpriteDrawable oldDown;
    private ImageButton adultButton;  
    private ImageButton youngButton;  
    private ImageButton youngAdultButton;  
    private ImageButton oldButton;  
    private boolean isVaccClicked = false;
    private ImageButton pause;
    private SpriteDrawable pausePressed;
    private ImageButton continueTime;
    private ImageButton faster;
    private SpriteDrawable continueTimePressed;
    private SpriteDrawable fasterPressed;
    private Texture fog;
    private Person currentPerson;
    protected static Music newDaySound;



    public static int vaccinatedYoung = 0;
    public static int vaccinatedYoungAdult = 0;
    public static int vaccinatedAdult = 0;
    public static int vaccinatedOld = 0;

    public boolean isMaskClicked = false;
    private Texture maskLogo = new Texture("masklogo3.png");

    private Texture vacBut = new Texture("vaccbut.jpg");
    private Texture novacBut = new Texture("novaccbut.jpg");
    private Texture blackBar = new Texture("blackçubuk.jpg");

    private BitmapFont fontInfo = new BitmapFont(Gdx.files.internal("InfoFont.fnt"));
    private BitmapFont fontInfoNewDay = new BitmapFont(Gdx.files.internal("NewDay.fnt"));
    
    float clock = 0;

    GridMap abstractMap;

    Texture hospital;
    Texture house;

    Texture[] buildings;

    public float period = 128f;
    public float timeSeconds = 0f;
    public int dayCount;
    

    public boolean closeSchool;
    public boolean curfewUnder18;
    public boolean curfewOver65;
    public boolean noWork;
    public boolean maskRule;

    public boolean[] curfews;
    public boolean[] daysBanned;


    public Simulation(GameMain game){
        this.game = game;
        dayCount = 0;
        font = fontInfo;
        newDayFont = fontInfoNewDay;
        viewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        createMusics();
        createButtons();
        addTimeButtons();
        addAllListeners();
        addMaskButton();
        addCurfewButton();
        addTurnBackButton();
        addVaccinationButton();
        addAnalyzeButton();
        addAdult();
        addYoung();
        addYoungAdult();
        addOld();
        stage.addActor(settings);
        stage.addActor(mask);
        stage.addActor(curfew);
        stage.addActor(turnBack);
        stage.addActor(vaccButton);
        stage.addActor(analyzeButton);
        stage.addActor(adultButton);
        stage.addActor(youngButton);
        stage.addActor(youngAdultButton);
        stage.addActor(oldButton);
        stage.addActor(pause);
        stage.addActor(continueTime);
        stage.addActor(faster);
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM, GameInfo.HEIGHT/GameInfo.PPM);
        box2DCamera.position.set((GameInfo.WIDTH/2f)/GameInfo.PPM , (GameInfo.HEIGHT/2f)/GameInfo.PPM,0);

        debugRenderer = new Box2DDebugRenderer(); 

        world = new World(new Vector2(0,0), true);

        world.setContactListener(this);

        bg = new Texture("MapBackground.png");
        gui = new Texture("SimulationGui.png");
        fog = new Texture("Adsız.png");

        abstractMap = new GridMap();

        population = new Population(world,abstractMap,GameInfo.population,this);
        //sound = Gdx.audio.newSound(Gdx.files.internal("Age Of War song.mp3"));
        box2DCamera.update();
        //sound.play();
        //sound.loop();
        createBuildings();


        hospital = new Texture("firsthospital.png");
        house = new Texture("firstHouse.png");

        debugRenderer.setDrawInactiveBodies(false);

        curfews = new boolean[6];
        daysBanned = new boolean[7];
    }
    

    private void addAnalyzeButton() {
        analyzeImage = new SpriteDrawable(new Sprite(new Texture("analyze.png") ));
        analyzeImageDown = new SpriteDrawable(new Sprite(new Texture("analyzedown.png") ));
        analyzeButton = new ImageButton(analyzeImage, analyzeImageDown);
        analyzeButton.setPosition(1390, 20);
        
        analyzeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.vaccinated.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.vaccinated);

            } 
        });
    }


    private void addTimeButtons() {
        continueTimePressed = new SpriteDrawable(new Sprite(new Texture("ContinueButtonPressed.png")));
        continueTime = new ImageButton(new SpriteDrawable(new Sprite(new Texture("ContinueButton.png"))),continueTimePressed,continueTimePressed);
        continueTime.setPosition(1799, 29);
        pausePressed = new SpriteDrawable(new Sprite(new Texture("PauseButtonPressed.png")));
        pause = new ImageButton(new SpriteDrawable(new Sprite(new Texture("PauseButton.png"))),pausePressed,pausePressed);
        pause.setPosition(1751, 29);
        fasterPressed = new SpriteDrawable(new Sprite(new Texture("FastForwardButtonPressed.png")));
        faster = new ImageButton(new SpriteDrawable(new Sprite(new Texture("FastForwardButton.png"))),fasterPressed,fasterPressed);
        faster.setPosition(1843, 29);
        continueTime.setChecked(true);
    }



    private void addAllListeners() {
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.play();   
                GameMain.beforeScreen = 3;            
                GameMain.stage = (Stage) GameMain.settingsScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.settingsScreen);
            }
        });
        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(pause.isChecked()){
                    continueTime.setChecked(false);
                    faster.setChecked(false);
                    pause();
                }
            }
        });
        continueTime.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(continueTime.isChecked()){
                    pause.setChecked(false);
                    faster.setChecked(false);
                }
            }
        });
        faster.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(faster.isChecked()){
                    pause.setChecked(false);
                    continueTime.setChecked(false);
                }
            }
        });
    }

    private void createButtons() {
        settings = new ImageButton(new SpriteDrawable(new Sprite(new Texture("SettingsButton.png"))));
        settings.setPosition(GameInfo.WIDTH/2f+200, GameInfo.HEIGHT/2f+460);
    }

    public void createBuildings(){
        buildings = new Texture[31];
        buildings[0] = new Texture("0.png");
        buildings[1] = new Texture("vHouse1.png");
        buildings[2] = new Texture("vHouse2.png");
        buildings[3] = new Texture("vHouse3.png");
        buildings[4] = new Texture("vHouse4.png");
        buildings[5] = new Texture("vHouse5.png");
        buildings[6] = new Texture("6.png");
        buildings[7] = new Texture("7.png");
        buildings[8] = new Texture("vHouse6.png");
        buildings[9] = new Texture("vHouse7.png");
        buildings[10] = new Texture("vHouse8.png");
        buildings[11] = new Texture("vHouse9.png");
        buildings[12] = new Texture("vHouse10.png");
        buildings[13] = new Texture("13.png");
        buildings[14] = new Texture("14.png");
        buildings[15] = new Texture("15.png");
        buildings[16] = new Texture("16.png");
        buildings[17] = new Texture("17.png");
        buildings[18] = new Texture("18.png");
        buildings[19] = new Texture("31.png");
        buildings[20] = new Texture("20.png");
        buildings[21] = new Texture("21.png");
        buildings[22] = new Texture("22.png");
        buildings[23] = new Texture("23.png");
        buildings[24] = new Texture("24.png");
        buildings[25] = new Texture("25.png");
        buildings[26] = new Texture("26.png");
        buildings[27] = new Texture("27.png");
        buildings[28] = new Texture("28.png");
        buildings[29] = new Texture("29.png");
        buildings[30] = new Texture("30.png");
    }

    public void renderBuildings(){

        game.getBatch().draw(buildings[0], 45  +45, GameInfo.HEIGHT - 45 - buildings[0].getHeight()-90);
        game.getBatch().draw(buildings[1], 355 +45, GameInfo.HEIGHT - 45 - buildings[1].getHeight()-90);
        game.getBatch().draw(buildings[2], 520 +45, GameInfo.HEIGHT - 45- buildings[2].getHeight()-90);
        game.getBatch().draw(buildings[3], 685 +45, GameInfo.HEIGHT - 45- buildings[3].getHeight()-90);
        game.getBatch().draw(buildings[4], 850 +45, GameInfo.HEIGHT - 45- buildings[4].getHeight()-90);
        game.getBatch().draw(buildings[5], 1015+45, GameInfo.HEIGHT - 45- buildings[5].getHeight()-90);
        game.getBatch().draw(buildings[6], 1180+45, GameInfo.HEIGHT - 45 - buildings[6].getHeight()-90);
        game.getBatch().draw(buildings[7], 1540+45, GameInfo.HEIGHT - 45 - buildings[7].getHeight()-90);
        game.getBatch().draw(buildings[8], 355 +45, GameInfo.HEIGHT -160- buildings[8].getHeight()-90);
        game.getBatch().draw(buildings[9], 520 +45, GameInfo.HEIGHT -160- buildings[9].getHeight()-90);
        game.getBatch().draw(buildings[10], 685+45, GameInfo.HEIGHT -160- buildings[10].getHeight()-90);
        game.getBatch().draw(buildings[11], 850+45, GameInfo.HEIGHT -160- buildings[11].getHeight()-90);
        game.getBatch().draw(buildings[12],1015+45, GameInfo.HEIGHT -160- buildings[12].getHeight()-90);
        game.getBatch().draw(buildings[13],45  +45, GameInfo.HEIGHT - 380 - buildings[13].getHeight()-90);
        game.getBatch().draw(buildings[14], 355+45, GameInfo.HEIGHT - 320 - buildings[14].getHeight()-90);
        game.getBatch().draw(buildings[15],470 +45, GameInfo.HEIGHT - 320 - buildings[15].getHeight()-90);
        game.getBatch().draw(buildings[16], 830+45, GameInfo.HEIGHT - 320 - buildings[16].getHeight()-90);
        game.getBatch().draw(buildings[17],1095+45, GameInfo.HEIGHT - 320 - buildings[17].getHeight()-90);
        game.getBatch().draw(buildings[18],1290+45, GameInfo.HEIGHT - 320 - buildings[18].getHeight()-90);
        game.getBatch().draw(buildings[19],1290+45, GameInfo.HEIGHT - 425 - buildings[19].getHeight()-90);
        game.getBatch().draw(buildings[20],1540+45, GameInfo.HEIGHT - 320 - buildings[20].getHeight()-90);
        game.getBatch().draw(buildings[21],45  +45, GameInfo.HEIGHT - 545 - buildings[21].getHeight()-90);
        game.getBatch().draw(buildings[22], 45 +45, GameInfo.HEIGHT - 635 - buildings[22].getHeight()-90);
        game.getBatch().draw(buildings[23],355 +45, GameInfo.HEIGHT - 545 - buildings[23].getHeight()-90);
        game.getBatch().draw(buildings[24], 470+45, GameInfo.HEIGHT - 545 - buildings[24].getHeight()-90);
        game.getBatch().draw(buildings[25],830 +45, GameInfo.HEIGHT - 590 - buildings[25].getHeight()-90);
        game.getBatch().draw(buildings[26],1045+45, GameInfo.HEIGHT - 590 - buildings[26].getHeight()-90);
        game.getBatch().draw(buildings[27],1290+45, GameInfo.HEIGHT - 590 - buildings[27].getHeight()-90);
        game.getBatch().draw(buildings[28],  45+45, GameInfo.HEIGHT - 750 - buildings[28].getHeight()-90);
        game.getBatch().draw(buildings[29], 355+45, GameInfo.HEIGHT - 750 - buildings[29].getHeight()-90);
        game.getBatch().draw(buildings[30],1045+45, GameInfo.HEIGHT - 740 - buildings[30].getHeight()-90);
    }

    public void newDay(){
        curfews = GameMain.curfewScreen.cases;
        daysBanned = GameMain.curfewScreen.days;

        if(curfews[0]){
            population.curfewUnder18();
        }
        else{
            population.removeCurfewUnder18();
        }

        if(curfews[1]){
            population.curfew19to40();
        }
        else{
            population.removeCurfew19to40();
        }
        
        if(curfews[2]){
            population.curfew40to65();
        }else{
            population.removeCurfew40to65();
        }
        if(curfews[3]){
            population.curfewOver65();
        }else{
            population.removeCurfewOver65();
        }
        if(curfews[4]){
            closeSchool = true;
        }else{
            closeSchool= false;
        }
        if(curfews[5]){ 
            noWork = true;
        }else{
            noWork = false;
        }

        if(daysBanned[0] && dayCount%7 == 0){
            population.fullCurfew();
        }
        else if(daysBanned[1] && dayCount%7 == 1){
            population.fullCurfew();
            
        }
        else if(daysBanned[2] && dayCount%7 == 2){
            population.fullCurfew();
        }
        else if(daysBanned[3] && dayCount%7 == 3){
            population.fullCurfew();
        }
        else if(daysBanned[4] && dayCount%7 == 4){
            population.fullCurfew();
        }
        else if(daysBanned[5] && dayCount%7 == 5){
            population.fullCurfew();
        }
        else if(daysBanned[6] && dayCount%7 == 6){
            population.fullCurfew();
        }
        population.startDay();
        GraphPlotter.dataSaver[dayCount][1] = population.susceptibleCount;
        GraphPlotter.dataSaver[dayCount][2] = population.infectedCount;
        GraphPlotter.dataSaver[dayCount][3] = population.removedCount;
        newDaySound.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        

        
        //manually looping the music list
        if(!musics[currentMusic].isPlaying()) {
            if(currentMusic == 4)
                currentMusic = -1;
            musics[++currentMusic].play();
        }
        timeSeconds +=Gdx.graphics.getDeltaTime();
        
        if(timeSeconds > period){
            timeSeconds = 0f;
            dayCount++;
            newDay();
        }


        population.updatePopulation();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing the background
        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);

        Color c = game.getBatch().getColor();
        renderBuildings();
        if(timeSeconds/period>0.67){
            game.getBatch().setColor(c.r, c.g, c.b, (float) (0.6 * ((timeSeconds/period-0.67)*3)));//set alpha to 0.3
            game.getBatch().draw(fog, 0, 0, 1920, 1080);
        }
        
        game.getBatch().setColor(c.r, c.g, c.b, 1f);//set alpha to 0.3

        //Drawing the population one by one
        for(int i = 0; i < population.getNumberOfPeople(); i++){
            currentPerson = population.getPopulation()[i];
            if(!currentPerson.isInBuilding)
                game.getBatch().draw(currentPerson,(currentPerson.getX() - currentPerson.getWidth()/2), (currentPerson.getY() - currentPerson.getHeight()/2));
        }


        game.getBatch().draw(gui, 0, 0);


        
        font.draw(game.getBatch(), "Infected: " + population.infectedCount, 90, GameInfo.HEIGHT-35);
        font.draw(game.getBatch(), "Immune: " + population.immuneCount, 460, GameInfo.HEIGHT-35);
        font.draw(game.getBatch(), "Dead: " + population.deadCount, 830, GameInfo.HEIGHT-35);
        if((int) (int) ((60/(period/16)) * (int) ((timeSeconds)%(period/16))) >= 10) {
            if((int) ((int)(timeSeconds)/(period/16)) + 8 < 10)
                font.draw(game.getBatch(),"Day: " + (dayCount + 1) + " / 0"  + (int) ((int)(timeSeconds) /(period/16)+ 8) + ":" + (int) ((60/(period/16)) * (int) ((timeSeconds)%(period/16))), GameInfo.WIDTH-400, GameInfo.HEIGHT-35);
            else
                font.draw(game.getBatch(),"Day: " + (dayCount + 1) + " / " + (int) ((int)(timeSeconds)/(period/16)+ 8) + ":" + (int) ((60/(period/16)) * (int) ((timeSeconds)%(period/16))), GameInfo.WIDTH-400, GameInfo.HEIGHT-35);
        }
        else {
            if((int) ((int)(timeSeconds)/(period/16)) + 8 < 10)
                font.draw(game.getBatch(),"Day: " + (dayCount + 1) + " / 0" + (int) ((int)(timeSeconds)/(period/16)+ 8) + ":0" + (int) ((60/(period/16)) * (int) ((timeSeconds)%(period/16))), GameInfo.WIDTH-400, GameInfo.HEIGHT-35);
            else
                font.draw(game.getBatch(),"Day: " + (dayCount + 1) + " / " + (int) ((int)(timeSeconds)/(period/16)+ 8) + ":0" + (int) ((60/(period/16)) * (int) ((timeSeconds)%(period/16))), GameInfo.WIDTH-400, GameInfo.HEIGHT-35);
        }

        if (maskRule){
            game.getBatch().draw(maskLogo,20 , 20);
        }

        if (isVaccClicked){
            game.getBatch().draw(vacBut,740 , 25);
        }
        else{
            game.getBatch().draw(novacBut,740 , 25);
        }

      
        game.getBatch().draw(blackBar, 400, 10);     
        game.getBatch().draw(blackBar, 720, 10); 
        game.getBatch().draw(blackBar, 1355, 10);         
        if(newDaySound.isPlaying()) 
            newDayFont.draw(game.getBatch(), "DAY " + dayCount, GameInfo.WIDTH/2-150, GameInfo.HEIGHT/2+150);
        game.getBatch().end();
        debugRenderer.render(world, box2DCamera.combined);


        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        box2DCamera.update();

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

    @Override
    public void beginContact(Contact contact) {
        Fixture firstBody = contact.getFixtureA();
        Fixture secondBody = contact.getFixtureB();
        
        Object[] firstUserData = (Object[])firstBody.getUserData();
        Object[] secondUserData = (Object[])secondBody.getUserData();


        String healthCondition1 = ((String) firstUserData[0]);
        
        String healthCondition2 = ((String) secondUserData[0]);

        if(GameInfo.trueWithPossibility((int)(GameInfo.rateOfSpread * 10))){
            if(maskRule){
                if(GameInfo.trueWithPossibility(50)){
                    if(healthCondition2.equals("Infe") && healthCondition1.equals("Susp") ){
                        firstUserData[0] = "Expo";
                        population.getPopulation()[(int)(firstUserData[1])].updateHealthCondition();
                        firstBody.setUserData(firstUserData);
                    }
                    else if(healthCondition1.equals("Infe") && healthCondition2.equals("Susp") ){
                        secondUserData[0] = "Expo";
                        population.getPopulation()[(int)(secondUserData[1])].updateHealthCondition();
                        secondBody.setUserData(secondUserData);
                    }
            
                    
                    if(healthCondition2.equals("Infe") && healthCondition1.equals("Expo") ){
                        firstUserData[2] = (int)firstUserData[2] + 1;
                        firstBody.setUserData(firstUserData); 
                    }
                    else if(healthCondition1.equals("Infe") && healthCondition2.equals("Expo") ){
                        secondUserData[2] = (int)secondUserData[2] + 1;
                        secondBody.setUserData(secondUserData);  
                    }
                }
            }
            else{
                if(healthCondition2.equals("Infe") && healthCondition1.equals("Susp") ){
                
                    firstUserData[0] = "Expo";
                    population.getPopulation()[(int)(firstUserData[1])].updateHealthCondition();
                    firstBody.setUserData(firstUserData);
                }
                else if(healthCondition1.equals("Infe") && healthCondition2.equals("Susp") ){
                    secondUserData[0] = "Expo";
                    population.getPopulation()[(int)(secondUserData[1])].updateHealthCondition();
                    secondBody.setUserData(secondUserData);
                }
        
                
                if(healthCondition2.equals("Infe") && healthCondition1.equals("Expo") ){
                    firstUserData[2] = (int)firstUserData[2] + 1;
                    firstBody.setUserData(firstUserData); 
                }
                else if(healthCondition1.equals("Infe") && healthCondition2.equals("Expo") ){
                    secondUserData[2] = (int)secondUserData[2] + 1;
                    secondBody.setUserData(secondUserData);  
                }
            }
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

    private void createMusics() {
        currentMusic = 3;
        musics = new Music[5];
        musics[0] = Gdx.audio.newMusic(Gdx.files.internal("music1.mp3"));
        musics[1] = Gdx.audio.newMusic(Gdx.files.internal("music2.mp3"));
        musics[2] = Gdx.audio.newMusic(Gdx.files.internal("music3.mp3"));
        musics[3] = Gdx.audio.newMusic(Gdx.files.internal("music4.mp3"));
        musics[4] = Gdx.audio.newMusic(Gdx.files.internal("music5.mp3"));
        newDaySound = Gdx.audio.newMusic(Gdx.files.internal("NewDaySound.mp3"));

        for(int i = 0; i < musics.length; i++) 
            musics[i].setVolume(1f);
        newDaySound.setVolume(1f);
    }

    public Stage getStage() {
        return stage;
    }

    void addMaskButton(){
        maskUp = new SpriteDrawable(new Sprite(new Texture("maskbutton.png") ));
        maskDown = new SpriteDrawable(new Sprite(new Texture("maskbuttonOnClick.png") ));
        mask = new ImageButton(maskUp, maskDown);
        mask.setPosition(100,20);
        
        mask.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                if (!maskRule){
                    maskRule =true;
                }
                else{
                    maskRule =false;
                }
                
            }
        });
        
    }
    private void addCurfewButton() {
        curfewUp = new SpriteDrawable(new Sprite(new Texture("curfewbutton.png") ));
        curfewDown = new SpriteDrawable(new Sprite(new Texture("curfewbuttonOnClick.png") ));
        curfew = new ImageButton(curfewUp, curfewDown);
        curfew.setPosition(420,20);
        
        curfew.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.curfewScreen.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.curfewScreen);
            }
        });
    }

    private void addTurnBackButton() {
        turnBack = new ImageButton(new SpriteDrawable(new Sprite(new Texture("SmallerTurnBack.png"))));
        turnBack.setPosition(GameInfo.WIDTH-80,GameInfo.HEIGHT-75);
        
        turnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                GameMain.popSound.play();
                GameMain.stage = GameMain.openingScreen.getButtons().getStage();
                game.setScreen(GameMain.openingScreen);
                Gdx.input.setInputProcessor(GameMain.openingScreen.getButtons().getStage());
            }
        });
    }
    private void addVaccinationButton() {
        vaccImage = new SpriteDrawable(new Sprite(new Texture("vaccination.png") ));
        vaccImageDown = new SpriteDrawable(new Sprite(new Texture("vaccinationdown.png") ));
        vaccButton = new ImageButton(vaccImage, vaccImageDown);
        vaccButton.setPosition(800, 20);
        
        vaccButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMain.popSound.stop();
                GameMain.popSound.play();
                GameMain.beforeScreen = 0;
                GameMain.stage = (Stage) GameMain.vaccinated.getStage();
                Gdx.input.setInputProcessor(GameMain.stage);
                game.setScreen(GameMain.vaccinated);
            } 
        });
    }
    private void addAdult() {
        adult = new SpriteDrawable(new Sprite(new Texture("Adult.JPG") ));
        adultDown = new SpriteDrawable(new Sprite(new Texture("AdultDown.JPG") ));                
        adultButton = new ImageButton(adult,adultDown);
        adultButton.setPosition(1110, 20);
        
        adultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                isVaccClicked = true;
                vaccinatedAdult++;
                population.randomAdultVaccine();
            }
        });
    }
    private void addYoung() {
        young = new SpriteDrawable(new Sprite(new Texture("Young.JPG") ));        
        youngDown = new SpriteDrawable(new Sprite(new Texture("YoungDown.JPG") ));        
        youngButton = new ImageButton(young,youngDown);
        youngButton.setPosition(1170, 20);
        
        youngButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                isVaccClicked = true;
                vaccinatedYoung++;
                population.randomYoungVaccine();
            }
        });
    }
    private void addYoungAdult() {
        youngadult = new SpriteDrawable(new Sprite(new Texture("YoungAdult.JPG") ));        
        youngadultDown = new SpriteDrawable(new Sprite(new Texture("YoungAdultDown.JPG") ));  
        youngAdultButton = new ImageButton(youngadult,youngadultDown);
        youngAdultButton.setPosition(1230, 20);
        
        youngAdultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                isVaccClicked = true;
                vaccinatedYoungAdult++;
                population.randomYoungAdultVaccine();
            }
        });
    }
    private void addOld() {
        old = new SpriteDrawable(new Sprite(new Texture("Old.JPG") ));  
        oldDown = new SpriteDrawable(new Sprite(new Texture("OldDown.JPG") ));              
        oldButton = new ImageButton(old,oldDown);
        oldButton.setPosition(1290, 20);
        
        oldButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {                
                isVaccClicked = true;
                vaccinatedOld++;
                population.randomOldVaccine();

            }
        });
    }
}
