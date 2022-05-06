package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;

import java.util.ArrayList;
import java.util.Arrays;

class PathFinder {
 
    private static final int NO_PARENT = -1;
 
    
    private static ArrayList dijkstra(int[][] adjacencyMatrix,
                                        int startVertex , int destination)
    {
        int nVertices = adjacencyMatrix[0].length;
 
       
        int[] shortestDistances = new int[nVertices];
 
        
        boolean[] added = new boolean[nVertices];
 
        for (int vertexIndex = 0; vertexIndex < nVertices;
                                            vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
         
        
        shortestDistances[startVertex] = 0;
 
       
        int[] parents = new int[nVertices];
 
     
        parents[startVertex] = NO_PARENT;
 
        
        for (int i = 1; i < nVertices; i++)
        {
 
           
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                     vertexIndex < nVertices;
                     vertexIndex++)
            {
                if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                    shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
 
          
            added[nearestVertex] = true;
 
            
            for (int vertexIndex = 0;
                     vertexIndex < nVertices;
                     vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];
                 
                if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                                                       edgeDistance;
                }
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        createPath(destination, parents, res);

        return res;

    }

 
   
    private static void createPath(int currentVertex,
                                  int[] parents, ArrayList<Integer> arrL)
    {
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        createPath(parents[currentVertex], parents, arrL);
        arrL.add(currentVertex);

    }
 
    public static void main(String[] args)
    {
        GridMap gm = new GridMap();



    

    
        System.out.println(dijkstra(gm.gridMap, 0, 155));
    }
}
 
