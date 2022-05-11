package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Task;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.WaitTill;

public class CurfewRoutine implements Routine{

    public Task[] taskList;

    public CurfewRoutine(Person person, Simulation menu, GridMap map) {

        taskList = new Task[2];

        taskList[0] = new WaitTill(person, GameInfo.randomBetween(0, 130), menu);

        taskList[1] = new WaitTill(person, 150, menu);


    }
    
}
