package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;

public class Person extends Sprite{
    public static int numberOfPerson = 0;
    public int id;

    private World world;
    private Body body;
    private int immunity;
    BodyDef bodyDef;
    Fixture fixture;

    String healthStatus;

    


    public Person(World world,String name, float x, float y, int immunity){
        super(new Texture(name));

        this.id = numberOfPerson;
        numberOfPerson++;

        this.world = world;
        bodyDef = new BodyDef();

        this.immunity = immunity;

        setPosition(x - getWidth()/2f, y- getHeight()/2f);
        healthStatus = "Heal";

        createBody();



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
        shape.setRadius(getWidth() * 5 / GameInfo.PPM);
        //shape.setAsBox((getWidth() * 10) / GameInfo.PPM, (getWidth() * 10) / GameInfo.PPM );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(healthStatus + id);

        //fikir: immuneların sensorlarını kapatalım <3 - tarik
        fixture.setSensor(true);

        shape.dispose();

        randomMove();

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

    public void checkBorder(){
        if(getX() < 0 || getX() >GameInfo.WIDTH){
            body.setLinearVelocity(-body.getLinearVelocity().x,body.getLinearVelocity().y );
        }
        if(getY() < 0 || getY() >GameInfo.HEIGHT){
            body.setLinearVelocity(body.getLinearVelocity().x,-body.getLinearVelocity().y );
        }
    }

    public int getImmunity() {
        return immunity;
    }
}
