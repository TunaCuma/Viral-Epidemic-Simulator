package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task;

import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;

public class Waiting implements Task{

    float firstTime;
    Simulation menu ;
    int time;
    Person person;

    public Waiting(Person person, int time, Simulation menu){
        this.person = person;
        this.time = time;
        this.menu = menu;

    }

    public void setFirstTime(){
        firstTime = menu.timeSeconds;
    }


    @Override
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
