package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task;

import java.util.ArrayList;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.PathFinder;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap.GridMap;

public class Moving implements Task{
    
    //instantiative variables
    public Person person;
    public ArrayList<Integer> path;
    public int targetLoc;
    public GridMap gm;
    public PathFinder pf;
    int pointer;
    int subTargetX;
    int subTargetY;

    /**
     * Using the pathFinder algorithm gets the person from a currentLoc to targetLoc
     * @param person Person that will be moved
     * @param gm GridMap object
     * @param currentLoc Current location as the integer of vertex
     * @param targetLoc 
     */
    public Moving(Person person, GridMap gm, int currentLoc, int targetLoc){
        this.gm = gm;
        this.person = person;

        this.targetLoc = targetLoc;

        pf = new PathFinder(gm);

        path = pf.dijkstra(currentLoc, targetLoc);

        subTargetX = (int)gm.vertices[path.get(pointer)].getX();
        subTargetY = (int)gm.vertices[path.get(pointer)].getY();

    }

    /**
     * This method executes every subtask in order. If the current task end pass to next subtask.
     * Subtask basically mean going from one location to other.
     */
    public void executeTaskOnBody(){
        if(isSubTaskEnd()){
            nextSubTask();
        }   
        person.goLocation(gm.vertices[path.get(pointer)]);
    }

    /**
     * @return returns true if subTask is ended. 
     * Every subtask is going from one vertex to another in graph.
     */
    public boolean isSubTaskEnd(){
        return (Math.abs(person.getX() - subTargetX) < 1 && Math.abs(person.getY() - subTargetY) < 1);
    }

    /**
     * @returns  target location as int
     */
    public int getTarget() {
        return this.targetLoc;
    }

    /**
     * @returns cur location as int
     */
    public int getCurLoc() {
        return this.getCurLoc();
    }

    /**
     * This method allow us to pass next subtask.
     * For example lets say current subtask is going from 1 to 3 and next subtask is going from 3 to 5. 
     * After firs subtask is finished this method allow to set subtask to going from 3 to 5.
     */
    public void nextSubTask(){
        if(isTaskEnd()){

        }
        else{
            pointer++;
            subTargetX = (int)gm.vertices[path.get(pointer)].getX();
            subTargetY = (int)gm.vertices[path.get(pointer)].getY();
        }
    }

    /**
     * @return if task end.
     */
    public boolean isTaskEnd(){
        if(pointer==path.size()-1){
            return isSubTaskEnd();
        }
        return false;
    }


    @Override
    public String toString() {
        return "M";
    }


}
