package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;

import java.util.Arrays;

public class Edge {
    
    public int xCoordinate;
    public int yCoordinate;
    boolean[] adjacenyList;
    int[] adjacenyDistanceList;

    DirectedGraph myGraph;



    static int numberOfNodes;
    int id;

    public Edge(DirectedGraph myGraph, int xCoordinate, int yCoordinate, boolean[] adjacenyList){
        id = numberOfNodes++;
        

        this.myGraph = myGraph;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.adjacenyList = adjacenyList;



    }

    public void createDistanceList(){
        adjacenyDistanceList = new int[adjacenyList.length];

        for(int i = 0; i < adjacenyList.length; i++){
            if(adjacenyList[i]){
                int differenceInX = Math.abs(this.xCoordinate - myGraph.edges[i].xCoordinate);
                int differenceInY = Math.abs(this.yCoordinate - myGraph.edges[i].yCoordinate);

                adjacenyDistanceList[i] = differenceInX + differenceInY;

                myGraph.numberOfVertices++;
            }
        }

    }

    public String toString(){
        return "ArrayNo : " + id +  " Adjaceny List: "+ Arrays.toString(adjacenyDistanceList);
    }


}
