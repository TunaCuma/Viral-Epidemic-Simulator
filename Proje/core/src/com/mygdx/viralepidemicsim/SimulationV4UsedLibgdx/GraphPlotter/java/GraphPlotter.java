package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.GraphPlotter.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GraphPlotter {
    
    public static int[][] dataSaver = new int[100][4];

    public static void setDataSaver(){
        for(int i = 0; i < 100; i++){
            dataSaver[i][0] = i;
        }
    }

    public static void plotGraph(){
        CreateFile.createFile();
        WriteToFile.writeToFile();


        ProcessBuilder processBuilder
        = new ProcessBuilder();

        List<String> builderList = new ArrayList<>();

        // add the list of commands to a list
        
        

        builderList.add("cmd.exe");
        builderList.add("/C");
        builderList.add("matlab -nosplash -r \"run DataPlotter.m\"");


    




        try {
            // Using the list , trigger the command
            processBuilder.command(builderList);
            Process process = processBuilder.start();

            // To read the output list
            BufferedReader reader
                = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : "
                            + exitCode);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
