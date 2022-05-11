package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers;

import com.badlogic.gdx.Gdx;

public class GameInfo {
    public static int WIDTH = 10000;
    public static int HEIGHT = 10000;
    public static float PPM = 1;
    public static float rateOfSpread = 1;
    public static float rateOfKill = 1; 


    public static void setRateOfKill(float db){
        rateOfKill = db;
    }

    public static void setRateOfSpread(float db){
        rateOfSpread = db;
    }

    public static void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
    }


    public static int randomBetween(int first, int second){
        return first + (int)((Math.random()) * (second-first));
    }

    public static boolean trueWithPossibility(int num){
        return randomBetween(0, 100) < num;
    }
}
