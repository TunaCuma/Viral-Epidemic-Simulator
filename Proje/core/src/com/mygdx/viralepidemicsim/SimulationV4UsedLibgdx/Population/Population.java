package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population;



import java.util.Random;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.FirstVersion.FinalVariables;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.FirstVersion.*;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;


public class Population {
    
    Person[] population;
    World world;


    public Population(World world, GridMap map, int numberOfPeople){
        population = new Person[numberOfPeople];
        this.world = world; 

        Random rand = new Random();

        
        
        int randomX;
        int randomY;
        
        for(int i = 0; i < population.length ; i++){
            randomX =  rand.nextInt(GameInfo.WIDTH);
            randomY = rand.nextInt(GameInfo.HEIGHT);
            

            double tempo = Math.random();
            float percentage = FinalVariables.YOUNG_PERCENTAGE/100;
            if(tempo < percentage) {
                population[i] = new Person(world,map,"Heal.png", randomX, randomY, FinalVariables.YOUNG_IMMUNITY);
            }
            else if( tempo < (percentage += FinalVariables.YOUNG_ADULT_PERCENTAGE/100)) {
                population[i] = new Person(world,map,"Heal.png", randomX, randomY, FinalVariables.YOUNG_ADULT_IMMUNITY);
            }
            else if( tempo < (percentage += FinalVariables.ADULT_PERCENTAGE/100)) {
                population[i] = new Person(world,map,"Heal.png", randomX, randomY, FinalVariables.ADULT_IMMUNITY);
            }
            else { //Old
                population[i] = new Person(world,map,"Heal.png", randomX, randomY, FinalVariables.OLD_IMMUNITY);
            }

        }
    }

   



    public void updatePopulation(){
        for(int i = 0; i < population.length ; i++){
            population[i].updatePerson();
        }
    }

    public int getNumberOfPeople(){
        return population.length;
    }

    public Person[] getPopulation(){
        return population;
    }


    public void healthUpdate(){
        for(int i = 0; i < population.length ; i++){
            population[i].updateHealthCondition();
        }
    }
}
