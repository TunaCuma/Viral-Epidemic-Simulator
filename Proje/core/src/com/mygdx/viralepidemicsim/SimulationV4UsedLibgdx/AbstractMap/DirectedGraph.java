package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;
import java.awt.Point;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Route.Route;

public class DirectedGraph {
    
    public Edge[] edges;

    int assigningPointer = 0;

    int numberOfEdges;
    int numberOfVertices;
    Route[] routes;

    public DirectedGraph(int numberOfEdges){
        this.numberOfEdges = numberOfEdges;
        edges = new Edge[numberOfEdges];

    }


    public void addEdge(Edge edge){

        edges[assigningPointer] = edge; 
        assigningPointer ++;

    }

    public void assignDistances(){
        for(int i = 0; i < assigningPointer; i++){
            edges[i].createDistanceList();
        }
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < edges.length ; i++){
            res = res + edges[i] + "\n";
        }
        return res;
    }

    public void createRoutes(){
        routes = new Route[numberOfVertices];

        




    }


}
