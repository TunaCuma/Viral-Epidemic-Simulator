package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population;



import java.util.Random;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.FirstVersion.FinalVariables;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.FirstVersion.*;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.MainMenu;


public class Population {
    
    Person[] population;
    World world;
    GridMap map;
    


    public Population(World world, GridMap map, int numberOfPeople, MainMenu menu){
        population = new Person[numberOfPeople];
        this.world = world; 
        this.map = map;

        Random rand = new Random();

        
        
        int randomBetween0to30;
    
        
        for(int i = 0; i < population.length ; i++){
            //randomBetween0to30 =  rand.nextInt(31);
            randomBetween0to30 =  0;

            double tempo = Math.random();
            float percentage = FinalVariables.YOUNG_PERCENTAGE/100;

            int xPosition = (int)map.vertices[randomBetween0to30].getX();
            int yPosition = (int)map.vertices[randomBetween0to30].getY();

            if(tempo < percentage) {
                population[i] = new Person(world,map,"Heal.png", xPosition , yPosition , FinalVariables.YOUNG_IMMUNITY,menu);
            }
            else if( tempo < (percentage += FinalVariables.YOUNG_ADULT_PERCENTAGE/100)) {
                population[i] = new Person(world,map,"Heal.png", xPosition, yPosition, FinalVariables.YOUNG_ADULT_IMMUNITY,menu);
            }
            else if( tempo < (percentage += FinalVariables.ADULT_PERCENTAGE/100)) {
                population[i] = new Person(world,map,"Heal.png", xPosition, yPosition, FinalVariables.ADULT_IMMUNITY,menu);
            }
            else { //Old
                population[i] = new Person(world,map,"Heal.png", xPosition, yPosition, FinalVariables.OLD_IMMUNITY,menu);
            }

            //population[i].assignCurrentLoc(randomBetween0to30);



        }
    }

   



    public void updatePopulation(){
        for(int i = 0; i < population.length ; i++){
            population[i].updatePerson();
            population[i].executeCurrentTask();
            
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

    public void executeTask(){
        
    }

    
}
