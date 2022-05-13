package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers;

import com.badlogic.gdx.Gdx;

public class GameInfo {
    public final static double ratioOfYoung = 33.2;
    public final static double ratioOfYoungAdult = 29.9;
    public final static double ratioOfAdult = 23.1;
    public final static double ratioOfOld = 13.8;
    
    public static int WIDTH = 10000;
    public static int HEIGHT = 10000;
    public static float PPM = 1;
    public static float rateOfSpread = 1;
    public static float rateOfKill = 1; 
    public static int population = 504;
    public static int initialPatient ;
    public static int numberOfVaccinatedInBegining; 


    public static void setPopulation(int num){
        population = num;
    }

    public static void setInitialPatient(int num){
        initialPatient = num;
    }

    public static void setNumberOfVaccinatedInBegining(int number){
        numberOfVaccinatedInBegining = number;
    }


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
