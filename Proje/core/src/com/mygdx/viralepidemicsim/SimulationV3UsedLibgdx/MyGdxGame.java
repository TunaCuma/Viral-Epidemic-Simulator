package com.mygdx.viralepidemicsim.SimulationV3UsedLibgdx;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	
	int WIDTH, HEIGHT;
	ShapeRenderer sp;
	SpriteBatch batch;
	Rectangle upperLimit, lowerLimit;
	Rectangle leftLimit, rightLimit;
	

	Person[] population = new Person[300];

	@Override
	public void create () {
		
		

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		sp = new ShapeRenderer();
		batch = new SpriteBatch();
		
		// Coordinates according to WIDTH and HEIGHT
		//initializePerson();

		for(int i = 0; i < population.length ; i++){
			population[i] = returnPerson();
			population[i].initSpeed(3);
			if((int)(2 * Math.random()) == 0){
				population[i].initSpeed(3);		
			}
		}
		population[0].getSick();
		
		// Parameters : Ball speed, Stick speed
		
	}

	@Override
	public void render () {
		// Main Loop
		
		for(int i = 0; i < population.length ; i++){
			logicUpdate(population[i]);
		}
		updateHealthStatus();
		updateInfection();
		updateHealthStatus();

		draw();
	}

	public Person returnPerson(){
		Random rand = new Random();
		int xPosition = rand.nextInt(WIDTH);
		int yPosition = rand.nextInt(HEIGHT);

		return new Person(xPosition + 4,yPosition + 4,8,8);
	}

	
	
	public void logicUpdate(Person ball){
		
		if(ball.getY() <= 0 || ball.getY() >= HEIGHT){
			ball.reverseY();	
		}

		
		if(ball.getX() > WIDTH){
			ball.reverseX();
		}else if(ball.getX() <= 0){
			ball.reverseX();
		}
		
		
		ball.increaseX(ball.getSpeedX());
		ball.decreaseY(ball.getSpeedY());
		
		//ball.logicUpdate(left, right);
	}
	
	public void draw(){
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sp.begin(ShapeType.Filled);
		
		for(int i = 0; i < population.length ; i++){
			population[i].setColor(population[i].healthStatus);
			sp.setColor(population[i].getColor());
			population[i].draw(sp);
		}
		
		sp.end();
		
		//batch.begin();
		//batch.end();
		
	}

	public void updateHealthStatus(){
        for(int i = 0; i < population.length - 1; i++){
            for(int  j = i ; j < population.length ; j++){
                
                if(population[i].healthStatus == 1 && population[j].healthStatus == 0 && population[i].isIntersect(population[j])){
                    population[j].getSick();
                }
                else if(population[i].healthStatus == 1 && population[j].healthStatus == 0 && population[i].isIntersect(population[j])){
                    population[i].getSick();
                }
            }
        }
    }

    public void updateInfection(){
        for(int i = 0; i < population.length ; i++){
            if(population[i].healthStatus == 1){
                population[i].immunity ++;
                if(population[i].immunity == 200){
                    population[i].getImmune();
                }
            }
            else if(population[i].healthStatus == 2){
                population[i].immunity--;
				population[i].immunity--;
                if(population[i].immunity == 0){
                    population[i].loseImmunity();
                }
            }
        }
    }

	public int randomBetween(int x, int y){
        return x + (int)(Math.random() * y);
    }
	
}