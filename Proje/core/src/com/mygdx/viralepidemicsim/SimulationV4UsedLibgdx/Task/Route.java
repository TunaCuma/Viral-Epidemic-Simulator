package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Route {
    
    
    public Point target;
    
    
    public Route(Point target){
        this.target = target;
        
    }
    

    public boolean isRouteEnd(int x, int y){
        float differenceInX = Math.abs(x - (int)target.getX());
        float differenceInY = Math.abs(y - (int)target.getY());

        return 5 > differenceInX + differenceInY;
    }

    

    public String toString(){
        return "Emre Akgül" ;
    }

    


}
