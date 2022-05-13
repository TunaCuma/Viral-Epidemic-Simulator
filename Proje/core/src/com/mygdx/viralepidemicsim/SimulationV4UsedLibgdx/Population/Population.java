package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Population;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;

public class Population {
    
    //instantiative variables
    public int infectedCount, immuneCount, deadCount;
    public int removedCount = immuneCount + deadCount;
    public int susceptibleCount = GameInfo.population - infectedCount - removedCount;

    public final static double ratioOfYoung = 33.2;
    public final static double ratioOfYoungAdult = 29.9;
    public final static double ratioOfAdult = 23.1;
    public final static double ratioOfOld = 13.8;


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
    


    /**
     * constructs the population objects
     * @param world world of the population
     * @param map map of the simulation
     * @param numberOfPeople desired number of the people
     * @param menu 
     */
    public Population(World world, GridMap map, int numberOfPeople, Simulation menu){
        population = new Person[numberOfPeople];
        this.world = world; 
        this.map = map;

        

        int houseCapacity = (numberOfPeople / 14);
        
        for(int i = 0; i < population.length ; i++){

            int randomBetween0to100 = GameInfo.randomBetween(0, 100);

        

            int[] houseIndexes = {1,2,3,4,8,9,11,12,18,19,21,23,28,30};

            int selectedHouse = i / houseCapacity;

            if(i / houseCapacity >= houseIndexes.length ){
                selectedHouse = houseIndexes.length-1;
            }


            int xPosition = (int)map.vertices[houseIndexes[selectedHouse]].getX();
            int yPosition = (int)map.vertices[houseIndexes[selectedHouse]].getY();

            


            if(randomBetween0to100 < GameInfo.ratioOfYoung) {
                population[i] = new Person(world,map,"Susp.png", xPosition , yPosition , menu,houseIndexes[selectedHouse], "Young");
            }
            else if( randomBetween0to100 < GameInfo.ratioOfYoung + GameInfo.ratioOfYoungAdult) {
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, menu,houseIndexes[selectedHouse], "YoungAdult");
            }
            else if( randomBetween0to100 < GameInfo.ratioOfYoung + GameInfo.ratioOfYoungAdult + GameInfo.ratioOfAdult ) {
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, menu,houseIndexes[selectedHouse], "Adult");
            }
            else { //Old
                population[i] = new Person(world,map,"Susp.png", xPosition, yPosition, menu,houseIndexes[selectedHouse], "Old");
            }

        }

        createYoungArrayList();
        createYoungAdultArrayList();
        createAdultArrayList();
        createOldArrayList();
    }

    public void updateCounts(){
        removedCount = immuneCount + deadCount;
        susceptibleCount = GameInfo.population - infectedCount - removedCount;
    }

   /**
     * Creates an arraylist that contain all young persons in population.
     */
    public void createYoungArrayList(){
        youngArr = new ArrayList<>();
        for(int i = 0; i < population.length ; i++){
            if(population[i].type.equals("Young")){
                youngArr.add(population[i]);
            }
        }
    }

    /**
     * Creates an arraylist that contain all young adult persons in population.
     */
    public void createYoungAdultArrayList(){
        youngAdultArr = new ArrayList<>();
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("YoungAdult")){
                youngAdultArr.add(population[i]);
            }
        }
    }

    /**
     * Creates an arraylist that contain all adult persons in population.
     */
    public void createAdultArrayList(){
        adultArr = new ArrayList<>();
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Adult")){
                adultArr.add(population[i]);
            }
        }
    }

    /**
     * Creates an arraylist that contain all old persons in population.
     */
    public void createOldArrayList(){
        oldArr = new ArrayList<>();
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Old")){
                oldArr.add(population[i]);
            }
        }
    }

    /**
     * After young array list created arraylist will be used for containing susceptible and exposed people. This method
     * updates young arraylist accordingly.
     */
    public void updateYoungArrayList(){
        ArrayList<Person> newYoungArr = new ArrayList<>();
        for(int i = 0; i < youngArr.size() ; i++){
            if((youngArr.get(i).healthStatus.equals("Susp")||youngArr.get(i).healthStatus.equals("Expo"))){
                newYoungArr.add(youngArr.get(i));
            }
        }

        youngArr = newYoungArr;
    }

    /**
     * After young adult array list created arraylist will be used for containing susceptible and exposed people. This method
     * updates young adult arraylist accordingly.
     */
    public void updateYoungAdultArrayList(){
        ArrayList<Person> newYoungAdultArr = new ArrayList<>();
        for(int i = 0; i < youngAdultArr.size(); i++){
            if((youngAdultArr.get(i).healthStatus.equals("Susp")||youngAdultArr.get(i).healthStatus.equals("Expo"))){
                newYoungAdultArr.add(youngAdultArr.get(i));
            }
        }

        youngAdultArr = newYoungAdultArr;
    }

    /**
     * After adult array list created arraylist will be used for containing susceptible and exposed people. This method
     * updates adult arraylist accordingly.
     */
    public void updateAdultArrayList(){
        ArrayList<Person> newAdultArr = new ArrayList<>();
        for(int i = 0; i < adultArr.size(); i++){
            if((adultArr.get(i).healthStatus.equals("Susp")||adultArr.get(i).healthStatus.equals("Expo"))){
                newAdultArr.add(adultArr.get(i));
            }
        }
        adultArr = newAdultArr;
    }

    /**
     * updates old arrayList
     */
    public void updateOldArrayList(){
        ArrayList<Person> newOldArr = new ArrayList<>();
        for(int i = 0; i < oldArr.size(); i++){
            if((oldArr.get(i).healthStatus.equals("Susp")||oldArr.get(i).healthStatus.equals("Expo"))){
                newOldArr.add(oldArr.get(i));
            }
        }

        oldArr = newOldArr;
    }

    /**
     * starts the day for every person of the population array
     */
    public void startDay(){
        for(int i = 0; i < population.length; i++){
            
            population[i].startDay();
        }
    }

    /**
     * calls the updatePerson() and executeCurrentTask() methods for every person object stored in the population array
     */
    public void updatePopulation(){
        for(int i = 0; i < population.length ; i++){
            population[i].updatePerson();
            population[i].executeCurrentTask();
        }
    }

    /**
     * 
     * @return number of people
     */
    public int getNumberOfPeople(){
        return population.length;
    }

    /**
     * 
     * @return array that contains persons in population.
     */
    public Person[] getPopulation(){
        return population;
    }

    /**
     * gets every person under 18 in curfew
     */
    public void curfewUnder18(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Young")){
                population[i].isInCurfew = true;
            }
        }
    }

    /**
     * removes Curfew under 18
     */
    public void removeCurfewUnder18(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Young")){
                population[i].isInCurfew = false;
            }
        }
    }

    /**
     * starts curfew for the people ove 65 years
     */
    public void curfewOver65(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Old")){
                population[i].isInCurfew = true;
            }
        }
    }

    /**
     * cancels the remove for the people over the age of 65
     */
    public void removeCurfewOver65(){
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Old")){
                population[i].isInCurfew = false;
            }
        }
    }

    /**
     * gets everyone in the curfew
     */
    public void fullCurfew(){
        for(int i = 0; i < population.length; i++){
            population[i].isInCurfew = true;
        }
    }

    /**
     * removes curfew for every person
     */
    public void removeFullCurfew() {
        for(int i = 0; i < population.length; i++){
            population[i].isInCurfew = false;
        }
    }

    /**
     * @return if every one is in curfew
     */
    public boolean isFullCurfew() {
        for(int i = 0; i < population.length; i++){
            if(population[i].isInCurfew == false)
                return false;
        }
        return true;
    }

    /**
     * Quarantines those aged 19 to 40.
     */
    public void curfew19to40() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("YoungAdult")){
                population[i].isInCurfew = true;
            }
        }
    }

    /**
     * Quarantines those aged 19 to 40.
     */
    public void curfew40to65() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Adult")){
                population[i].isInCurfew = true;
            }
        }
    }

    /**
     * Removes quarantines those aged 40 to 65.
     */
    public void removeCurfew40to65() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("Adult")){
                population[i].isInCurfew = false;
            }
        }
    }

    
    /**
     * Removes quarantines those aged 19 to 40.
     */
    public void removeCurfew19to40() {
        for(int i = 0; i < population.length; i++){
            if(population[i].type.equals("YoungAdult")){
                population[i].isInCurfew = false;
            }
        }
    }

    /**
     * Vaccinates a random person.
     */
    public void randomVaccine(){
        int random = GameInfo.randomBetween(0, 4);

        if(random == 0){
            randomYoungVaccine();
        }
        else if(random ==1){
            randomYoungAdultVaccine();
        }
        else if(random == 2){
            randomAdultVaccine();
        }
        else{
            randomOldVaccine();
        }
    }

    /**
     * vaccinates a randomly selected young person
     */
    public void randomYoungVaccine(){
        updateYoungArrayList();

        if(youngArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, youngArr.size());

            youngArr.get(randomNumber).getImmune();
        }

    }

    /**
     * vaccinates a randomly selected young adult person
     */
    public void randomYoungAdultVaccine(){
        updateYoungAdultArrayList();

        if(youngAdultArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, youngAdultArr.size());

            youngAdultArr.get(randomNumber).getImmune();
        }

    }

    /**
     * vaccinates a randomly selected adult person
     */
    public void randomAdultVaccine(){
        updateAdultArrayList();

        if(adultArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, adultArr.size());

            adultArr.get(randomNumber).getImmune();
        }

    }

    /**
     * Kill a random person.
     */
    public void killRandom() {
        int ran = GameInfo.randomBetween(0, population.length);
        if (population[ran].body.isActive()) {
        population[ran].die();
        }
    }

    /**
     * vaccinates a randomly selected old person
     */
    public void randomOldVaccine(){
        updateOldArrayList();

        if(oldArr.size()>0){
            int randomNumber = GameInfo.randomBetween(0, oldArr.size());

            oldArr.get(randomNumber).getImmune();
        }
        
    }

    /**
     * vaccinates a randomly selected person
     */
    public void randomVaccination(int number){
        for(int i = 0; i < number; i++){
            int randNum = GameInfo.randomBetween(0, population.length);
            if((population[randNum].healthStatus.equals("Susp"))){
                population[randNum].getImmune();
            }
            else{
                i--;
            }
        }
    }

    /**
     * gets a random person infected
     * @param number
     */
    public void randomInfection(int number){
        for(int i = 0 ; i < number ; i++){
            population[GameInfo.randomBetween(0, population.length)].getInfected();
        }
    }

    /**
     * @return return number of active person in population.
     */
    public int isActiveCount() {
        int counter = 0;
        for (int i = 0; i<population.length; i++) {
            if (population[i].body.isActive()) {
                counter++;
            }
        }
        return counter;
    }

   
}
