package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person;

import java.awt.Point;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.DirectedGraph;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Route.Route;

public class Person extends Sprite{
    public static int numberOfPerson = 0;
    public int id;

    private World world;
    private Body body;
    private int immunity;
    BodyDef bodyDef;
    Fixture fixture;

    String healthStatus;

    Route[] routes;
    int routeNumber = -1;
    Route currentRoute;

    


    public Person(World world,DirectedGraph map,String name, float x, float y, int immunity){
        super(new Texture(name));

        this.id = numberOfPerson;
        numberOfPerson++;

        this.world = world;
        bodyDef = new BodyDef();

        this.immunity = immunity;

        setPosition(x - getWidth()/2f, y- getHeight()/2f);
        healthStatus = "Heal";

        createBody();

        newRoute();

        createRoutes(map);
        nextRoute();
        
    }

    public void randomRoute(){
        
    }

    public void newRoute(){
        Vector2 targetPosition = new Vector2(750,500);
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

    public void createRoutes(DirectedGraph graph){
        routes = new Route[31];

        //routes[0] = new Route(graph.edges[15]);
        //routes[1] = new Route(graph.edges[13]);
        //routes[2] = new Route(graph.edges[8]);
        //routes[3] = new Route(graph.edges[1]);
        //routes[4] = new Route(graph.edges[0]);
        //routes[5] = new Route(graph.edges[17]);
        //routes[6] = new Route(graph.edges[4]);

        routes[0]= new Route(new Point(295,GameInfo.HEIGHT - 152));
        routes[1]= new Route(new Point(405,GameInfo.HEIGHT - 45));
        routes[2]= new Route(new Point(570,GameInfo.HEIGHT - 45));
        routes[3]= new Route(new Point(735,GameInfo.HEIGHT - 45));
        routes[4]= new Route(new Point(900,GameInfo.HEIGHT - 45));
        routes[5]= new Route(new Point(1100,GameInfo.HEIGHT - 145));
        routes[6]= new Route(new Point(1480,GameInfo.HEIGHT -160));
        routes[7]= new Route(new Point(1540,GameInfo.HEIGHT -200));
        routes[8]= new Route(new Point(405,GameInfo.HEIGHT -260));
        routes[9]= new Route(new Point(570,GameInfo.HEIGHT -260));
        routes[10]= new Route(new Point(735,GameInfo.HEIGHT -260));
        routes[11]= new Route(new Point(900,GameInfo.HEIGHT -260));
        routes[12]= new Route(new Point(1100,GameInfo.HEIGHT -260));
        routes[13]= new Route(new Point(100,GameInfo.HEIGHT -480));
        routes[14]= new Route(new Point(355,GameInfo.HEIGHT -490));
        routes[15]= new Route(new Point(570,GameInfo.HEIGHT -320));
        routes[16]= new Route(new Point(830,GameInfo.HEIGHT -400));
        routes[17]= new Route(new Point(1150,GameInfo.HEIGHT -320));
        routes[18]= new Route(new Point(1350,GameInfo.HEIGHT -320));
        routes[19]= new Route(new Point(1350,GameInfo.HEIGHT -530));
        routes[20]= new Route(new Point(1540,GameInfo.HEIGHT -500));
        routes[21]= new Route(new Point(100,GameInfo.HEIGHT -545));
        routes[22]= new Route(new Point(295,GameInfo.HEIGHT -700));
        routes[23]= new Route(new Point(355,GameInfo.HEIGHT -600));
        routes[24]= new Route(new Point(770,GameInfo.HEIGHT -650));
        routes[25]= new Route(new Point(900,GameInfo.HEIGHT -590));
        routes[26]= new Route(new Point(1150,GameInfo.HEIGHT -590));
        routes[27]= new Route(new Point(1490,GameInfo.HEIGHT -590));
        routes[28]= new Route(new Point(100,GameInfo.HEIGHT -840));
        routes[29]= new Route(new Point(600,GameInfo.HEIGHT -840));
        routes[30]= new Route(new Point(1145,GameInfo.HEIGHT -840));


    }   

    public void trackRoute(){   
        

        if(currentRoute.isRouteEnd((int)body.getPosition().x , (int)body.getPosition().y )){
            nextRoute();
        }
        else{
            Vector2 targetPosition = new Vector2((float)currentRoute.target.getX(),(float)currentRoute.target.getY());
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
        
    }

    public void nextRoute(){
        //if(routeNumber < routes.length - 1){
        //    routeNumber++;
        //    currentRoute = routes[routeNumber];
        //}
        //else{
        //    body.setAwake(false);
        //}
        
        Random rand = new Random();
        int randomInt = rand.nextInt(31);
        
        currentRoute = routes[randomInt];

    }

    public void randomMove(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(8);

        if(randomNumber == 0){
            body.setLinearVelocity(30,15);
        }
        else if(randomNumber == 1){
            body.setLinearVelocity(15,30);
        }
        else if(randomNumber == 2){
            body.setLinearVelocity(-30,15);
        }
        else if(randomNumber == 3){
            body.setLinearVelocity(-15,-30);
        }
        else if(randomNumber == 4){
            body.setLinearVelocity(30,-15);
        }
        else if(randomNumber == 5){
            body.setLinearVelocity(15,-30);
        }
        else if(randomNumber == 6){
            body.setLinearVelocity(-30,-15);
        }
        else if(randomNumber == 7){
            body.setLinearVelocity(-15,-30);
        }
    
        
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
