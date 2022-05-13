package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population.Population;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.AdultRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.CurfewRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.InfectedRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.OldRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.RandomRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.Routine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.YoungAdultRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.YoungRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Moving;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Task;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Waiting;

public class Person extends Sprite{
    public static int numberOfPerson = 0;
    public int id;

    public int healDay = -1;

    public Simulation menu;

    private World world;
    public Body body;
    BodyDef bodyDef;
    public Fixture fixture;
    public boolean isInBuilding;

    public String healthStatus;

    GridMap map;

    public Task currentTask;

    Task[] taskList;

    public int currentLoc;

    int pointer;

    public String type;

    public int homeLocation;

    public Routine routine;

    public double maskValue =1;


    public int workLoc;

    public boolean isInCurfew;

    public float dieCoefficient;

    public static float targetSpeed = 5f;


    public Person(World world, GridMap gm, String name, float x, float y, Simulation menu, int home, String type){
        super(new Texture(name));
        isInBuilding = true;
        this.menu = menu;
        this.id = numberOfPerson;
        this.homeLocation = home;
        numberOfPerson++;

        this.type = type;
        if(type.equals("Young")){
            dieCoefficient = 0.3f;
        }
        else if(type.equals("YoungAdult")){
            dieCoefficient = 0.4f;
        }
        else if(type.equals("Adult")){
            dieCoefficient = 0.7f;
        }
        else{
            dieCoefficient = 1;
        }

        this.world = world;
        bodyDef = new BodyDef();


        this.map = gm;

        setPosition(x - getWidth()/2f, y- getHeight()/2f);
        healthStatus = "Susp";

        createBody();

       
        
        setHome(home);

        if(type.equals("Adult")||type.equals("YoungAdult")){
            assignWork();
        }


        enterBuilding(home);




        startDay();

    
    }

    /**
     * This method assign a work to person. Currently assigning work basicly means assigning a workplace.
     * Every person will go to their workplace in the begining of day then spend a considerable amount of time there.
     */
    public void assignWork(){
        int randomBetween0to100 = GameInfo.randomBetween(0, 100);
        if(randomBetween0to100 < 45){
            workLoc = 27;
        }
        else if(randomBetween0to100 < 50){
            workLoc = 0;
        }
        else if(randomBetween0to100 < 55){
            workLoc = 5;
        }
        else if(randomBetween0to100 < 60){
            workLoc = 7;
        }
        else if(randomBetween0to100 < 65){
            workLoc = 10;
        }
        else if(randomBetween0to100 < 70){
            workLoc = 14;
        }
        else if(randomBetween0to100 < 75){
            workLoc = 15;
        }
        else if(randomBetween0to100 < 80){
            workLoc = 16;
        }
        else if(randomBetween0to100 < 85){
            workLoc = 17;
        }
        else if(randomBetween0to100 < 90){
            workLoc = 22;
        }
        else if(randomBetween0to100 < 95){
            workLoc = 25;
        }
        else{
            workLoc = 26;
        }


    }

    
    /**
     * This method get person exposed. It is called when susceptible person encounter with infected person.
     */
    public void getExposed() {
        this.healthStatus = "Expo";
    }

    /**
     * In the start of everyday this method is called for every person.
     * Method is mainly responsible for updating health status accordingly.
     * Method checks if person will infect, heal or die. Infection and dying is
     * depends on possibility.
     * In addition It checks and enforces curfews according to age ranges from the array 
     * called curfews and starts a new day for the population. For example, 
     * if it has decided to apply curfew under the age of 18, it applies it to 
     * that age group and starts the new day. 
     * And lastly assign routine to the person according to its health condition. 
     *
     */
    public void startDay(){
        if (menu.dayCount == healDay) {
            getImmune();
            Simulation.population.infectedCount--;
        }
        Object[] userData = (Object[])fixture.getUserData();
        healthStatus = (String)((userData)[0]);

        if(healthStatus.equals("Infe")){
            
            double possiblity = 100 * GameInfo.rateOfKill * dieCoefficient;

            
            if(GameInfo.randomBetween(0, 100) < possiblity && menu.dayCount > 0){
                die();
            }   
            
        }
        else if(healthStatus.equals("Expo")){
            
            double possiblity = 100;

            boolean isInfected = false;
            for(int i = 0; i < (int)userData[2]; i++){
                if(GameInfo.trueWithPossibility((int)possiblity) ){
                    getInfected();
                    isInfected = true;
                    break;
                }
            }

            if(!isInfected){
                userData[0] = "Susp";
                userData[2] = 0;
            }
            
            updateHealthCondition();
            
            fixture.setUserData(userData);

        } 
        
        
        
        currentLoc = homeLocation;
        assignRoutine();
        pointer=0;
        currentTask = taskList[0];


    }


    /**
     * Everyday person objects will have a routine.
     * This method assign a routine to the person.
     * These are the current routine choices.
     * 
     * Standard routines are assigned if there is no curfew and person is not infected.
     * Standard routines : YoungRoutine - YoungAdultRoutine - AdultRoutine - OldRoutine 
     * 
     * Infected routine : If person is infected this routine will be assigned.
     * 
     * Curfew routine : If person is not infected and there is a curfew going on this routine will be assigned.
     */
    private void assignRoutine() {
        Object[] userData = (Object[])fixture.getUserData();
        healthStatus = (String)((userData)[0]);

        if(healthStatus.equals("Infe")){
            routine = new InfectedRoutine(this, menu, map);
            taskList =  ((InfectedRoutine)routine).taskList;
        }
        else if(isInCurfew){
            routine = new CurfewRoutine(this, menu, map);
            taskList =  ((CurfewRoutine)routine).taskList;
        }
        else{
            if(this.type.equals("Young")) {
                routine = new YoungRoutine(this, menu, map);
                taskList = ((YoungRoutine)routine).taskList;
            }
            else if (this.type.equals("Young Adult")) {
                routine = new YoungAdultRoutine(this, menu, map);
                taskList = ((YoungAdultRoutine)routine).taskList;
            }
            else if (this.type.equals("Adult")) {
                routine = new AdultRoutine(this, menu, map);
                taskList = ((AdultRoutine)routine).taskList;
            }
            else if (this.type.equals("Old")) {
                routine = new OldRoutine(this, menu, map);
                taskList = ((OldRoutine)routine).taskList;
            }
            else {
                routine = new RandomRoutine(this, menu, map);
                taskList = ((RandomRoutine)routine).taskList;
            }
        }

        
    }

    /**
     * 
     * @param buildingIndex index of building to set as home.
     * This method will be called in the constructor and set persons home.
     * Every person will start the day in his/her home and end it in again.
     * 
     */
    public void setHome(int buildingIndex){
        homeLocation = buildingIndex;
        currentLoc = homeLocation;
    }

    /**
     *  This method execute current task if current task is not end.
     * Will pass to next task if current task end.
     */
    public void executeCurrentTask(){
        if(isTaskEnd()){
            if(currentTask.toString().equals("M")){
                currentLoc = ((Moving)currentTask).targetLoc;
                enterBuilding(currentLoc);
            }
            else if(currentTask.toString().equals("W")){
                exitBuilding(currentLoc);
            }
            else if(pointer!=6 && currentTask.toString().equals("WT")){
                exitBuilding(currentLoc);
            }
            nextTask();
        }
        else{
            executeTask(taskList[pointer]);
        }
    }
    
    /**
     * todo
     * @param buildingVertexNumber
     */
    public void exitBuilding(int buildingVertexNumber) {
        this.map.buildings[buildingVertexNumber].removePerson(this);
    }

    /**
     * todo
     * @param buildingVertexNumber
     */
    public void enterBuilding(int buildingVertexNumber) {
        this.map.buildings[buildingVertexNumber].addPerson(this);
    }

    /**
     * This method allow person to pass next task.
     * It will be called in execute current task.
     */
    public void nextTask(){
        pointer++;
        currentTask = taskList[pointer];
        if(currentTask.toString().equals("W")){
            ((Waiting) currentTask).setFirstTime();
        }
        getBody().setAwake(true);

    }
    
    /**
     * @return if current task end.
     */
    public boolean isTaskEnd(){
        return currentTask.isTaskEnd();
    }

    /**
     * 
     * @param task task to execute.
     * This method allow task to be executed in the body.
     * 
     */
    public void executeTask(Task task){
        task.executeTaskOnBody();
    }
    
    /**
     * todo
     * @param target
     */
    public void goLocation(Point target) {
        Vector2 targetPosition = new Vector2((float)target.getX(),(float)target.getY());
        

        Vector2 direction = targetPosition.sub(body.getPosition());


        float distanceToTravel = direction.len();

        // For most of the movement, the target speed is ok
        float speedToUse = targetSpeed;

        // Check if this speed will cause overshoot in the next time step.
        // If so, we need to scale the speed down to just enough to reach
        // the target point. (Assuming here a step length based on 60 fps)
        float distancePerTimestep = speedToUse / 60.0f;
        if ( distancePerTimestep > distanceToTravel )
            speedToUse *= ( distanceToTravel / distancePerTimestep ) * 3;

        // The rest is pretty much what you had already:
        Vector2 desiredVelocity = direction.scl(speedToUse);
        Vector2 changeInVelocity = desiredVelocity.sub(body.getLinearVelocity());
        
        Vector2 force = changeInVelocity.scl(body.getMass()*60.0f);
        body.applyForce(force, body.getWorldCenter(), true);
    }


    /**
     * todo
     */
    void createBody(){

        //a static body does not affected by gravity or other forces.
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX() , getY() );

        body = world.createBody(bodyDef);
        
        
        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() * 3.2f );
        //shape.setAsBox((getWidth() * 10) / GameInfo.PPM, (getWidth() * 10) / GameInfo.PPM );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;



        fixture = body.createFixture(fixtureDef);

        Object[] userData = new Object[3];
        userData[0] = healthStatus;
        userData[1] = id;
        userData[2] = 0;


        fixture.setUserData(userData);

        updateHealthCondition();


        //fikir: immuneların sensorlarını kapatalım <3 - tarik
        fixture.setSensor(true);

        shape.dispose();

    }

    /**
     * This method allow us to set persons location to bodys location.
     * 
     */
    public void updatePerson(){
        if(body.getLinearVelocity().x > 0){
            setPosition((body.getPosition().x ), body.getPosition().y );
        }
        else if(body.getLinearVelocity().x < 0){
            setPosition((body.getPosition().x ), body.getPosition().y );
        }
    }

    /**
     * This method will  be called in constructor. Infect person.
     */
    public void firstInfection(){
        Object[] userData = (Object[])fixture.getUserData();

        
        userData[0] = "Infe";
        
    
        fixture.setUserData(userData);

        updateHealthCondition();
    }

    /**
     * This method infect person.
     * Infecting a person basically means changing persons fixtures userDatas first index (health status) to "Infe".
     * By changing fixture health status of the person will be reachable from simulation.
     */
    public void getInfected(){
        healDay = menu.dayCount + 7;

        Object[] userData = (Object[])fixture.getUserData();

        
        userData[0] = "Infe";
        
    
        fixture.setUserData(userData);

        updateHealthCondition();


        Simulation.population.infectedCount++;


    }
    

    /**
     * This method allow person to get immunity.
     * Immune persons will not affected by infection.
     *  */    
    public void getImmune(){
        Object[] userData = (Object[])fixture.getUserData();
        if(!userData[0].equals("Immu")){

            userData[0] = "Immu";
        
    
            fixture.setUserData(userData);

            updateHealthCondition();


            Simulation.population.immuneCount++;
        }

    }

    /**
     * This method updates health condition of person according to its fixture.
     * Fixture is updating in the simulation every time there is a necessary change.
     */
    public void updateHealthCondition(){
        healthStatus = (String)(((Object[])fixture.getUserData())[0]);
        if(healthStatus.equals("Susp"))
            setTexture(Population.SUSP_TEXTURE);
        if(healthStatus.equals("Infe"))
            setTexture(Population.INFE_TEXTURE);
        if(healthStatus.equals("Expo"))
            setTexture(Population.EXPO_TEXTURE);
        if(healthStatus.equals("Immu"))
            setTexture(Population.IMMU_TEXTURE);
    }

    /**
     * 
     * @return body of person.
     */
    public Body getBody(){
        return body;
    }

    /**
     * This method is for killing person. Person will die after this method called and
     * there wont be any change in that person.
     */
    public void die(){
        Object[] userData = (Object[])fixture.getUserData();

        
        userData[0] = "Dead";
        
    
        fixture.setUserData(userData);

        updateHealthCondition();

        body.setActive(false);



        Simulation.population.deadCount++;
        Simulation.population.infectedCount--;
    }
       
   
}
