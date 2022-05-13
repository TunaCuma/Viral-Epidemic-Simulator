package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;

public class Waiting implements Task{

    //instantiative variables
    float firstTime;
    Simulation menu ;
    int time;
    Person person;

    /**
     * constructor for waiting object
     * @param person person that will wait
     * @param time time for waiting
     * @param menu Simulation object
     */
    public Waiting(Person person, int time, Simulation menu){
        this.person = person;
        this.time = time;
        this.menu = menu;

    }

    /**
     * sets the first time to check if it is the time to end waiting
     */
    public void setFirstTime(){
        firstTime = menu.timeSeconds;
    }


    @Override
    /**
     * by setting awake to false this method makes the person wait
     */
    public void executeTaskOnBody() {
        person.getBody().setAwake(false);

    }

    @Override
    public boolean isTaskEnd() {
        return menu.timeSeconds - firstTime > time;
        
    }

    @Override
    public String toString() {
        return "W";
    }
}
