package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population;



import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.FirstVersion.FinalVariables;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
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

    ArrayList<Person> youngArr;
    ArrayList<Person> youngAdultArr;
    ArrayList<Person> adultArr;
    ArrayList<Person> oldArr;
    

    public Population(World world, GridMap map, int numberOfPeople, Simulation menu){
        population = new Person[numberOfPeople];
        this.world = world; 
        this.map = map;

        infectedCount=1;

        
        
        for(int i = 0; i < population.length ; i++){

            int randomBetween0to100 = GameInfo.randomBetween(0, 100);

        

            int[] houseIndexes = {1,2,3,4,8,9,11,12,18,19,21,23,28,30};


            int xPosition = (int)map.vertices[houseIndexes[i/36]].getX();
            int yPosition = (int)map.vertices[houseIndexes[i/36]].getY();


            if(randomBetween0to100 < 33.2) {
                population[i] = new Person(world,map,"Susp.png", xPosition , yPosition , FinalVariables.YOUNG_IMMUNITY,menu,houseIndexes[i/36], "Young");
            }
            else if( randomBetween0to100 < 63.1) {
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, FinalVariables.YOUNG_ADULT_IMMUNITY,menu,houseIndexes[i/36], "YoungAdult");
            }
            else if( randomBetween0to100 < 86.2 ) {
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, FinalVariables.ADULT_IMMUNITY,menu,houseIndexes[i/36], "Adult");
            }
            else { //Old
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, FinalVariables.OLD_IMMUNITY,menu,houseIndexes[i/36], "Old");
            }

        }

        createYoungArrayList();
        createYoungAdultArrayList();
        createAdultArrayList();
        createOldArrayList();
    }

    public void createYoungArrayList(){
        youngArr = new ArrayList<>();
        for(int i = 0; i < population.length ; i++){
            if(population[i].type.equals("Young")){
                youngArr.add(population[i]);
            }
        }
    }

    public void createYoungAdultArrayList(){
        youngAdultArr = new ArrayList<>();
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("YoungAdult")){
                youngAdultArr.add(population[i]);
            }
        }
    }

    public void createAdultArrayList(){
        adultArr = new ArrayList<>();
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Adult")){
                adultArr.add(population[i]);
            }
        }
    }

    public void createOldArrayList(){
        oldArr = new ArrayList<>();
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Old")){
                oldArr.add(population[i]);
            }
        }
    }

    public void updateYoungArrayList(){
        ArrayList<Person> newYoungArr = new ArrayList<>();
        for(int i = 0; i < youngArr.size() ; i++){
            if((youngArr.get(i).healthStatus.equals("Susp")||youngArr.get(i).healthStatus.equals("Expo"))){
                newYoungArr.add(youngArr.get(i));
            }
        }

        youngArr = newYoungArr;
    }

    public void updateYoungAdultArrayList(){
        ArrayList<Person> newYoungAdultArr = new ArrayList<>();
        for(int i = 0; i < youngAdultArr.size(); i++){
            if((youngAdultArr.get(i).healthStatus.equals("Susp")||youngAdultArr.get(i).healthStatus.equals("Expo"))){
                newYoungAdultArr.add(youngAdultArr.get(i));
            }
        }

        youngAdultArr = newYoungAdultArr;
    }

    public void updateAdultArrayList(){
        ArrayList<Person> newAdultArr = new ArrayList<>();
        for(int i = 0; i < adultArr.size(); i++){
            if((adultArr.get(i).healthStatus.equals("Susp")||adultArr.get(i).healthStatus.equals("Expo"))){
                newAdultArr.add(adultArr.get(i));
            }
        }
        adultArr = newAdultArr;
    }

    public void updateOldArrayList(){
        ArrayList<Person> newOldArr = new ArrayList<>();
        for(int i = 0; i < oldArr.size(); i++){
            if((oldArr.get(i).healthStatus.equals("Susp")||oldArr.get(i).healthStatus.equals("Expo"))){
                newOldArr.add(oldArr.get(i));
            }
        }

        oldArr = newOldArr;
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

    public void curfewUnder18(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Young")){
                population[i].isInCurfew = true;
            }
        }
    }

    public void removeCurfewUnder18(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Young")){
                population[i].isInCurfew = false;
            }
        }
    }

    public void curfewOver65(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Old")){
                population[i].isInCurfew = true;
            }
        }
    }

    public void removeCurfewOver65(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Old")){
                population[i].isInCurfew = false;
            }
        }
    }

    public void fullCurfew(){
        for(int i = 0; i < population.length; i++){
            population[i].isInCurfew = true;
        }
    }

    public void removeFullCurfew() {
        for(int i = 0; i < population.length; i++){
            population[i].isInCurfew = false;
        }
    }

    public boolean isFullCurfew() {
        for(int i = 0; i < population.length; i++){
            if(population[i].isInCurfew == false)
                return false;
        }
        return true;
    }

    public void curfew19to40() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("YoungAdult")){
                population[i].isInCurfew = true;
            }
        }
    }

    public void curfew40to65() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Adult")){
                population[i].isInCurfew = true;
            }
        }
    }

    public void removeCurfew40to65() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Adult")){
                population[i].isInCurfew = false;
            }
        }
    }

    public void removeCurfew19to40() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("YoungAdult")){
                population[i].isInCurfew = false;
            }
        }
    }

    public void randomVaccine(){
        
    }

    public void randomYoungVaccine(){
        updateYoungArrayList();

        if(youngArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, youngArr.size());

            youngArr.get(randomNumber).getImmune();
        }

    }

    public void randomYoungAdultVaccine(){
        updateYoungAdultArrayList();

        if(youngAdultArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, youngAdultArr.size());

            youngAdultArr.get(randomNumber).getImmune();
        }

    }

    public void randomAdultVaccine(){
        updateAdultArrayList();

        if(adultArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, adultArr.size());

            adultArr.get(randomNumber).getImmune();
        }

    }

    public void randomOldVaccine(){
        updateOldArrayList();

        if(oldArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, oldArr.size());

            oldArr.get(randomNumber).getImmune();
        }
        
    }

   
}
