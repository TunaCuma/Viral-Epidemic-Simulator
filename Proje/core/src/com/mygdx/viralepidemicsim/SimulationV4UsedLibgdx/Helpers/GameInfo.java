package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers;

import com.badlogic.gdx.Gdx;

public class GameInfo {

    //FINAL variables for the program
    public final static double ratioOfYoung = 33.2;
    public final static double ratioOfYoungAdult = 29.9;
    public final static double ratioOfAdult = 23.1;
    public final static double ratioOfOld = 13.8;
    
    //instantiative variables
    public static int WIDTH = 10000;
    public static int HEIGHT = 10000;
    public static float rateOfSpread = 1;
    public static float rateOfKill = 1; 
    public static int population = 504;
    public static int initialPatient ;
    public static int numberOfVaccinatedInBegining; 

    /**
     * sets population
     * @param num population int
     */
    public static void setPopulation(int num){
        population = num;
    }

    /**
     * sets the initial patient number as set at the beginning of the game as set in the parameter screen
     * @param num int initial patient num
     */
    public static void setInitialPatient(int num){
        initialPatient = num;
    }

    /**
     * sets the number of vaccinated people at the beginning of the game as set in the parameter screen
     * @param number int numberOfVaccinatedInBegining
     */
    public static void setNumberOfVaccinatedInBegining(int number){
        numberOfVaccinatedInBegining = number;
    }

    /**
     * sets the rate of kill at the beggining of the game as set in the parameter screen.
     * @param db float rateOfKill
     */
    public static void setRateOfKill(float db){
        rateOfKill = db;
    }

    /**
     * sets the rate of spread at the beginning of the game as set in the parameter screen.
     * @param db float rateOfSpread
     */
    public static void setRateOfSpread(float db){
        rateOfSpread = db;
    }

    /**
     * sets the width and the height
     */
    public static void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
    }

    /**
     * returns a random integer between the int first and the int second (first inclusive, second exclusive)
     * @param first
     * @param second
     * @return random int
     */
    public static int randomBetween(int first, int second){
        return first + (int)((Math.random()) * (second-first));
    }

    /**
     * returns true with the input percentage
     * @param num input percentage 
     * @return true with the input percentage
     */
    public static boolean trueWithPossibility(int num){
        return randomBetween(0, 100) < num;
    }
}
