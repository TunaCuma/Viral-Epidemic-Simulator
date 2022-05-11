package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Building;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
public class Building {
    
    Point location;
    int numberOfPerson;
    String name;
    public double size;
    Random rand;
    
    //this variable determines how likely it is to get infected in this building
    public double coefficient;

    //double size;
    ArrayList<Person> persons = new ArrayList<Person>(); 

    public Building(String name, Point location, double size){
        this.name = name;
        this.location = location;
        this.size = size;
        coefficient = 10;
        rand =  new Random();
    }

    public int getInfectedCount() {
        int counter = 0;
        for (int i = 0; i<persons.size(); i++) {
            if (persons.get(i).healthStatus.equals("Infe")) {
                counter++;
            }
        }
        return counter;
    }

    public void rollDice() {
        int num = GameInfo.randomBetween(1, (int)size);
        if (num < this.coefficient * persons.size() * persons.size() * getInfectedCount()) {
            getRandomExposed();
        }
    }

    public void getRandomExposed() {
        int a = GameInfo.randomBetween(0, persons.size());
        persons.get(a).getExposed();
    }

    public void addPerson(Person person) {
        persons.add(person);
        person.isInBuilding = true;
        person.body.setActive(false);
        if (persons.size()>2) {
            rollDice();
        }
    }

    public void removePerson(Person person) {
        persons.remove(person);
        person.isInBuilding = false;
        person.body.setActive(true);
    }







}
