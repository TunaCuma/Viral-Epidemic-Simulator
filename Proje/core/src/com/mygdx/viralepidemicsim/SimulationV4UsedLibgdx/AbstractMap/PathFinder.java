package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;

import java.util.ArrayList;

public class PathFinder {
 
    private static final int NO_PARENT = -1;
    
    ArrayList<ArrayList<Integer>> everyPath;
    GridMap map;

    public PathFinder(GridMap map){
        everyPath = new ArrayList<>();
        this.map = map;
    }

    
    public ArrayList<Integer> dijkstra(int startVertex , int destination)
    {
        int nVertices = map.gridMap[0].length;
 
       
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
                int edgeDistance = map.gridMap[nearestVertex][vertexIndex];
                 
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


 
}
 
