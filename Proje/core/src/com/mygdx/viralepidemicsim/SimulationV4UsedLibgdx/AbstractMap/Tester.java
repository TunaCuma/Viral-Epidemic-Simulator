package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;

import java.awt.Point;

import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Person.Person;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Task.Waiting;
public class Tester {
    public static void main(String[] args) {
        
        Point emre = new Point(10,10);
        Point rifat = new Point(15,10);

        System.out.println(emre.distance(rifat));

        String str = "Faik";

        GridMap gm = new GridMap();

        for(int i = 0; i < 30; i++){
            System.out.println(    GameInfo.trueWithPossibility(10) );

        }
       
    
    }
}
