package com.mygdx.viralepidemicsim;

import java.util.ArrayList;

public class GameGenerator {
    ArrayList<Person> persons;
    public GameGenerator(int population) {
        persons = new ArrayList<Person>(population-1);
        for(int i = 0; i < population; i++) {
            persons.add(randomPersonGenerator());
        }
    }
    public Person randomPersonGenerator() {
        return new Old(); //Will be fixed later.
    }
}
