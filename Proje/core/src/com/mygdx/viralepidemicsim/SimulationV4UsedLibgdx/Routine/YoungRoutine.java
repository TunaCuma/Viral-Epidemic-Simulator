package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Routine;
import java.util.Random;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.Simulation;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Moving;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Task;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.WaitTill;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Waiting;

public class YoungRoutine implements Routine{
            

    public Task[] taskList;
    public int buildingList[] = new int[]{0,7,10,15,16,22,24,25,29};

    public YoungRoutine(Person person, Simulation sim,GridMap gm){

        taskList = new Task[7];

        int temp1;
        int temp2;

        Random rand = new Random();

        taskList[0] = new WaitTill(person,rand.nextInt(20),sim);

        temp1 = buildingList[rand.nextInt(buildingList.length)];

        taskList[1] = new Moving(person,gm , person.currentLoc , temp1);

        taskList[2] = new Waiting(person, rand.nextInt(12), sim);

        temp2 = buildingList[rand.nextInt(buildingList.length)];
        taskList[3] = new Moving(person, gm, temp1, temp2);

        taskList[4] = new Waiting(person, rand.nextInt(12), sim);

        taskList[5] = new Moving(person, gm, temp2, person.homeLocation);

        taskList[6] = new WaitTill(person, 130,sim);
    }
}
