package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task;

public interface Task {
    
    
    public void executeTaskOnBody();

    public boolean isTaskEnd();

    @Override
    String toString();
}
