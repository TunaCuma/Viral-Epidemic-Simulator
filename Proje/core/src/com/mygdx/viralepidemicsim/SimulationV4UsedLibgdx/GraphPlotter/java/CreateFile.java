package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.GraphPlotter.java;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
  

    public static void createFile() {
        try {
        File myObj = new File("C:\\Users\\Emrea\\Desktop\\Viral-Epidemic-Simulator\\faik.txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}