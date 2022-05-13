package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task;

public interface Task {
    
    /**
     * This method executes task on body. Every subclass will implement this method
     * according to its logic.
     */
    public void executeTaskOnBody();

    /**
     * @return if task end
     */
    public boolean isTaskEnd();

    /**
     * 
     * @return First letter of classes name.
     */
    @Override
    String toString();
}
