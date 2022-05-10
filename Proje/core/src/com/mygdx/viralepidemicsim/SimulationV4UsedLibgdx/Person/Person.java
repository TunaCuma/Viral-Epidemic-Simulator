package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person;

import java.awt.Point;
import java.util.Random;
import java.util.logging.Handler;

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
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.AdultRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.OldRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.RandomRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.Routine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.YoungAdultRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine.YoungRoutine;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Moving;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Task;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.WaitTill;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Waiting;

public class Person extends Sprite{
    public static int numberOfPerson = 0;
    public int id;

    public Simulation menu;

    private World world;
    public Body body;
    private int immunity;
    BodyDef bodyDef;
    public Fixture fixture;

    String healthStatus;

    GridMap map;

    public Task currentTask;

    Task[] taskList;

    public int currentLoc;

    int pointer;

    String type;

    public int homeLocation;

    public Routine routine;

    public double maskValue =1;

    

    public Person(World world, GridMap gm, String name, float x, float y, int immunity, Simulation menu, int home, String type){
        super(new Texture(name));
        this.menu = menu;
        this.id = numberOfPerson;
        this.homeLocation = home;
        numberOfPerson++;

        this.type = type;

        this.world = world;
        bodyDef = new BodyDef();

        this.immunity = immunity;

        this.map = gm;

        setPosition(x - getWidth()/2f, y- getHeight()/2f);
        healthStatus = "Susp";

        createBody();

        //taskList = new Task[62];
        
        setHome(home);

        enterBuilding(home);

        startDay();
        

        //taskList[0] = new WaitTill(this, 30, menu);

        //taskList[1] = new Moving(this, gm, currentLoc, currentLoc - 1);

        //taskList[2] = new WaitTill(this, 100, menu);

        //int last= currentLoc;
        //int now = 0;
        //for(int i = 0; i < 31; i++){
        //    now = (int)(Math.random()*31);
        //    taskList[2*i] = new Moving(this, gm, last, now);
        //    taskList[2*i+1] = new Waiting(this, 3, menu);
        //    last = now;
        //}

    

    }

    public void startDay(){
        Object[] userData = (Object[])fixture.getUserData();
        healthStatus = (String)((userData)[0]);
        if(healthStatus.equals("Expo")){
            
        
            double possiblity = 100 * maskValue;

            for(int i = 0; i < (int)userData[2]; i++){
                if(randomBetween(0, 100) < possiblity){
                    getInfected();
                    break;
                }
            }

            userData[0] = "Susp";
            userData[2] = 0;
            
            fixture.setUserData(userData);

        }       
        
        
        currentLoc = homeLocation;
        assignRoutine();
        pointer=0;
        currentTask = taskList[0];


    }

    public int randomBetween(int first, int second){

        return first + (int)(Math.random() * (second - first));
    }


    private void assignRoutine() {
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

    public void setHome(int buildingIndex){
        homeLocation = buildingIndex;
        currentLoc = homeLocation;
    }

    public void executeCurrentTask(){
        if(isTaskEnd()){
            if(currentTask.toString().equals("M")){
                System.out.println(((Moving)currentTask).targetLoc);
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

    public void exitBuilding(int buildingVertexNumber) {
        this.map.buildings[buildingVertexNumber].removePerson(this);
    }

    public void enterBuilding(int buildingVertexNumber) {
        this.map.buildings[buildingVertexNumber].addPerson(this);
    }

    public void nextTask(){
        pointer++;
        currentTask = taskList[pointer];
        if(currentTask.toString().equals("W")){
            ((Waiting) currentTask).setFirstTime();
        }
        getBody().setAwake(true);

    }
    
    public boolean isTaskEnd(){
        return currentTask.isTaskEnd();
    }

    public void executeTask(Task task){
        task.executeTaskOnBody();
    }
    
    public void goLocation(Point target) {
        Vector2 targetPosition = new Vector2((float)target.getX(),(float)target.getY());
        float targetSpeed = 5f;

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



    void createBody(){

        //a static body does not affected by gravity or other forces.
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX() / GameInfo.PPM , getY() / GameInfo.PPM);

        body = world.createBody(bodyDef);
        
        
        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() * 3.2f / GameInfo.PPM);
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

        //fikir: immuneların sensorlarını kapatalım <3 - tarik
        fixture.setSensor(true);

        shape.dispose();

    }

    
    public String makeProperIndex(int index){
        if(index<10){
            return "00"+index;
        }
        if(index<100){
            return "0"+index;
        }
        return "" + index;
    }

    public void updatePerson(){
        if(body.getLinearVelocity().x > 0){
            setPosition((body.getPosition().x )* GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        }
        else if(body.getLinearVelocity().x < 0){
            setPosition((body.getPosition().x )* GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        }
    
    }

    public void getInfected(){
        Object[] userData = (Object[])fixture.getUserData();

        healthStatus = "Infe";
        userData[0] = healthStatus;
    
        fixture.setUserData(userData);
        setTexture(new Texture("Infe.png"));
        menu.population.infectedCount++;
    }
    public void putMask(){
        this.maskValue = 0.1;        
    }
    

    public void updateHealthCondition(){
        healthStatus = (String)(((Object[])fixture.getUserData())[0]);
        String texture = healthStatus + ".png";
        setTexture(new Texture(texture));
    }

    public Body getBody(){
        return body;
    }



    public int getImmunity() {
        return immunity;
    }
       
    public void makeImmune(){
        fixture.setUserData("Immu"+ id);
    }
}
