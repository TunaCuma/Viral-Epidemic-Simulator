package com.mygdx.viralepidemicsim.SimulationV3UsedLibgdx;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Person extends SimulationObject{

	int WIDTH = Gdx.graphics.getWidth();
	int HEIGHT = Gdx.graphics.getHeight();

	private int speedx, speedy;

	int healthStatus; //0 for healthy. 1 for sick. 2 for immune.
    static final int INFECTION_TIME = 3000;
    int immunity = 0;
	
	public Person(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	
	public void reset(int WIDTH, int HEIGHT){
		Random random = new Random();
		
		setX(random.nextInt(WIDTH));
		setY(random.nextInt(HEIGHT));
		
		if(random.nextInt(100)%2 == 1){
			setSpeedY(3);
		}else{
			setSpeedY(-3);
		}
	}
	
	@Override
	public void initSpeed(int speed){
		if(randomBetween(0, 2) == 0){
			speedx = randomBetween(1, 3);
		}
		else{
			speedx = -randomBetween(1, 3);
		}

		if(randomBetween(0, 2) == 0){
			speedy = randomBetween(1, 3);
		}
		else{
			speedy = -randomBetween(1, 3);
		}

	}
	
	public void setSpeedX(int speed){
		speedx = speed;
	}
	
	public void setSpeedY(int speed){
		speedy = speed;
	}
	
	public void reverseX(){
		speedx = -speedx;
	}
	
	public void reverseY(){
		speedy = -speedy;
	}
	
	public int getSpeedX(){
		return speedx;
	}

	public int getSpeedY(){
		return speedy;
	}

	public void setColor(int healthStatus){
		if(healthStatus == 0){
			this.setColor(Color.BLUE);
		}
		else if(healthStatus == 1){
			this.setColor(Color.RED);
		}
		else{
			this.setColor(Color.GREEN);
		}
	}

	public boolean isIntersect(Person other){
        return this.isOverlappingWith(other);
    }

	public void getSick(){
        healthStatus = 1;
    }

    public void getImmune(){
        healthStatus = 2;
    }

    public void loseImmunity(){
        healthStatus = 0;
    }

	public int randomBetween(int x, int y){
        return x + (int)(Math.random() * y);
    }
	
}