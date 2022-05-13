package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Moving;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Task;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.WaitTill;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Waiting;

public class InfectedRoutine implements Routine{
    
    //Task array for the routine
    public Task[] taskList;

    //hospital location as int for the vertex index of the map
    public int hospitalLoc = 0;

    /**
     * constructor for adult routine. Assigns 5 different tasks. assigns tasks for the person to go to the hospital and back to the house assigned
     * @param person Person whose routine will be determined
     * @param sim simulation object
     * @param gm GridMap object
     */
    public InfectedRoutine(Person person, Simulation sim, GridMap gm){
     
        taskList = new Task[5];

        taskList[0] = new WaitTill(person,GameInfo.randomBetween(0, 18),sim);

        taskList[1] = new Moving(person, gm, person.homeLocation, hospitalLoc);

        taskList[2] = new Waiting(person, GameInfo.randomBetween(45, 60), sim);

        taskList[3] = new Moving(person, gm, 0, person.homeLocation);

        taskList[4] = new WaitTill(person, 130, sim);
        

    }




}
