package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.GraphPlotter.java;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


public class WriteToFile {
  

    public static void writeToFile() {
        try {
        FileWriter myWriter = new FileWriter("C:\\Users\\Emrea\\Desktop\\Viral-Epidemic-Simulator\\faik.txt");

        GraphPlotter.setDataSaver();
        int[][] arr = GraphPlotter.dataSaver;
        
        
        for(int i = 0; i < arr.length; i++){
            myWriter.write(arr[i][0] + ",");
            myWriter.write(arr[i][1] + ",");
            myWriter.write(arr[i][2] + ",");
            myWriter.write(arr[i][3] + "\n");
        }
        
        myWriter.close();
        System.out.println("Successfully wrote to the file.");

        


        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}