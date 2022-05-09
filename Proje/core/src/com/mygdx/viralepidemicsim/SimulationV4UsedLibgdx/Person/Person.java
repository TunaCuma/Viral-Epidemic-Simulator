package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person;

import java.awt.Point;
import java.util.Random;

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
    Fixture fixture;

    String healthStatus;

    GridMap map;

    public Task currentTask;

    Task[] taskList;

    int currentLoc;

    int pointer;

    int homeLocation;

    public Person(World world, GridMap gm, String name, float x, float y, int immunity, Simulation menu, int home){
        super(new Texture(name));
        this.menu = menu;
        this.id = numberOfPerson;
        numberOfPerson++;

        this.world = world;
        bodyDef = new BodyDef();

        this.immunity = immunity;

        this.map = gm;

        setPosition(x - getWidth()/2f, y- getHeight()/2f);
        healthStatus = "Heal";

        createBody();

        taskList = new Task[62];
        
        setHome(home);


        taskList[0] = new WaitTill(this, 30, menu);

        taskList[1] = new Moving(this, gm, currentLoc, currentLoc - 1);

        taskList[2] = new WaitTill(this, 100, menu);

        //int last= currentLoc;
        //int now = 0;
        //for(int i = 0; i < 31; i++){
        //    now = (int)(Math.random()*31);
        //    taskList[2*i] = new Moving(this, gm, last, now);
        //    taskList[2*i+1] = new Waiting(this, 3, menu);
        //    last = now;
        //}

    

        currentTask = taskList[0];
    }

    public void setHome(int buildingIndex){
        homeLocation = buildingIndex;
        currentLoc = homeLocation;
    }

    public void executeCurrentTask(){
        if(isTaskEnd()){
            nextTask();

        }
        else{
            executeTask(taskList[pointer]);
        }
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

    public void assignTask(){
        Random rand = new Random();
        int randNum = rand.nextInt(31);

        taskList[pointer] = new Moving(this, map, currentLoc,randNum);
        currentTask = taskList[pointer];
        pointer++;
    }

    public void assignCurrentLoc(int currentLoc){
        this.currentLoc = currentLoc;

    }

    public void executeTask(Task task){
        task.executeTaskOnBody();
    }
    

    public void goLocation(Point target) {
        Vector2 targetPosition = new Vector2((float)target.getX(),(float)target.getY());
        float targetSpeed = 2.5f;

        Vector2 direction = targetPosition.sub(body.getPosition());


        float distanceToTravel = direction.len();

        // For most of the movement, the target speed is ok
        float speedToUse = targetSpeed;

        // Check if this speed will cause overshoot in the next time step.
        // If so, we need to scale the speed down to just enough to reach
        // the target point. (Assuming here a step length based on 60 fps)
        float distancePerTimestep = speedToUse / 60.0f;
        if ( distancePerTimestep > distanceToTravel )
            speedToUse *= ( distanceToTravel / distancePerTimestep ) * 2;

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
        shape.setRadius(getWidth() * 4 / GameInfo.PPM);
        //shape.setAsBox((getWidth() * 10) / GameInfo.PPM, (getWidth() * 10) / GameInfo.PPM );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(healthStatus + id);

        //fikir: immuneların sensorlarını kapatalım <3 - tarik
        fixture.setSensor(true);

        shape.dispose();

    }

    public void updatePerson(){
        if(body.getLinearVelocity().x > 0){
            setPosition((body.getPosition().x )* GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        }
        else if(body.getLinearVelocity().x < 0){
            setPosition((body.getPosition().x )* GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        }
    
    }

    public void makePatientZero(){
        healthStatus = "Sick";
        fixture.setUserData(healthStatus + id);
        setTexture(new Texture("Sick.png"));
    }

    public void updateHealthCondition(){
        healthStatus = ((String) fixture.getUserData()).substring(0,4);
        String texture = healthStatus + ".png";
        setTexture(new Texture(texture));
    }

    public Body getBody(){
        return body;
    }



    public int getImmunity() {
        return immunity;
    }
}
