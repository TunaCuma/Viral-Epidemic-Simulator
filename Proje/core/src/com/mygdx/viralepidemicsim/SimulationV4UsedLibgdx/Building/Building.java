package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Building;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
public class Building {
    
    //instantiative variables
    Point location;
    int numberOfPerson;
    String name;
    public double size;
    Random rand;
    
    //this variable determines how likely it is to get infected in this building
    public double coefficient;

    //double size;
    ArrayList<Person> persons = new ArrayList<Person>(); 

    /**
     * @param name name of the building
     * @param location location of the building in the map
     * @param size size of the building
     */
    public Building(String name, Point location, double size){
        this.name = name;
        this.location = location;
        this.size = size;
        coefficient = 3;
        rand =  new Random();
    }

    /**
     * @return the number of infected people in the building
     */
    public int getInfectedCount() {
        int counter = 0;
        for (int i = 0; i<persons.size(); i++) {
            if (persons.get(i).healthStatus.equals("Infe")) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * rolls the dice and decides if someone in the building will get exposed
     */
    public void rollDice() {
        int num = GameInfo.randomBetween(1, (int)size);
        if (num < this.coefficient * persons.size() * persons.size() * getInfectedCount()) {
            getRandomExposed();
        }
    }

    /**
     * gets a randomly selected person in the building exposed
     */
    public void getRandomExposed() {
        int a = GameInfo.randomBetween(0, persons.size());
        persons.get(a).getExposed();
    }

    /**
     * adds a person into the building's Person[]
     * @param person person that will be added
     */
    public void addPerson(Person person) {
        persons.add(person);
        person.isInBuilding = true;
        person.body.setActive(false);
        if (persons.size()>2) {
            rollDice();
        }
    }

    /**
     * removes a person from the building's Person[]
     * @param person the person that will be removed.
     */
    public void removePerson(Person person) {
        persons.remove(person);
        person.isInBuilding = false;
        person.body.setActive(true);
    }







}
