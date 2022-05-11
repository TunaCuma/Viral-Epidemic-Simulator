package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population;



import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.FirstVersion.FinalVariables;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;

public class Population {
    
    public int infectedCount, immuneCount, deadCount;
    public Person[] population;
    World world;
    GridMap map;
    public final static Texture EXPO_TEXTURE = new Texture("Expo.png");
    public final static Texture INFE_TEXTURE = new Texture("Infe.png");
    public final static Texture SUSP_TEXTURE = new Texture("Susp.png");
    public final static Texture IMMU_TEXTURE = new Texture("Immu.png");

    public Population(World world, GridMap map, int numberOfPeople, Simulation menu){
        population = new Person[numberOfPeople];
        this.world = world; 
        this.map = map;

        Random rand = new Random();

        int randomBetween0to30;
    
        
        for(int i = 0; i < population.length ; i++){
            randomBetween0to30 =  0;

            double tempo = Math.random();
            float percentage = FinalVariables.YOUNG_PERCENTAGE/100;

            int[] houseIndexes = {1,2,3,4,8,9,11,12,18,19,21,23,28,30};


            int xPosition = (int)map.vertices[houseIndexes[i/36]].getX();
            int yPosition = (int)map.vertices[houseIndexes[i/36]].getY();


            if(tempo < percentage) {
                population[i] = new Person(world,map,"Susp.png", xPosition , yPosition , FinalVariables.YOUNG_IMMUNITY,menu,houseIndexes[i/36], FinalVariables.YOUNG);
            }
            else if( tempo < (percentage += FinalVariables.YOUNG_ADULT_PERCENTAGE/100)) {
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, FinalVariables.YOUNG_ADULT_IMMUNITY,menu,houseIndexes[i/36], FinalVariables.YOUNG_ADULT_NAME);
            }
            else if( tempo < (percentage += FinalVariables.ADULT_PERCENTAGE/100)) {
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, FinalVariables.ADULT_IMMUNITY,menu,houseIndexes[i/36], FinalVariables.ADULT_NAME);
            }
            else { //Old
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, FinalVariables.OLD_IMMUNITY,menu,houseIndexes[i/36], FinalVariables.OLD_NAME);
            }
            
            
            




        }
    }

    public void startDay(){
        for(int i = 0; i < population.length; i++){
            population[i].startDay();
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
    public void wearMask(){
        for (Person humans : population){
            humans.putMask();
        }
    }
    public void removeMask(){
        for (Person humans : population){
            humans.noMask();
        }
    }
}
