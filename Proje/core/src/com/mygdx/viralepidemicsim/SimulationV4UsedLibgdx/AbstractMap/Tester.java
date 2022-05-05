package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;

import java.awt.Point;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Route.Route;

public class Tester {
    
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(18);

        boolean[] isThereVertex0 = {false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,true};
        Edge edge0 = new Edge(graph, 35 , GameInfo.HEIGHT - 35,isThereVertex0.clone() );

        boolean[] isThereVertex1 = {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge1 = new Edge(graph, 95 , GameInfo.HEIGHT - 35,isThereVertex1.clone() );

        boolean[] isThereVertex2 = {false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge2 = new Edge(graph, 40 , GameInfo.HEIGHT - 40,isThereVertex2.clone() );

        boolean[] isThereVertex3 = {false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge3 = new Edge(graph, 90 , GameInfo.HEIGHT - 40,isThereVertex3.clone() );

        boolean[] isThereVertex4 = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true};
        Edge edge4 = new Edge(graph, 45 , GameInfo.HEIGHT - 75,isThereVertex4.clone() );

        boolean[] isThereVertex5 = {false,false,false,false,false,false,false,true,true,false,false,false,true,false,false,false,false,false};
        Edge edge5 = new Edge(graph, 35 , GameInfo.HEIGHT - 110,isThereVertex5.clone() );

        boolean[] isThereVertex6 = {false,false,false,false,false,false,false,true,true,false,false,false,false,false,false,false,true,false};
        Edge edge6 = new Edge(graph, 40 , GameInfo.HEIGHT - 110,isThereVertex6.clone() );

        boolean[] isThereVertex7 = {false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false};
        Edge edge7 = new Edge(graph, 90 , GameInfo.HEIGHT - 110,isThereVertex7.clone() );

        boolean[] isThereVertex8 = {false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge8 = new Edge(graph, 95 , GameInfo.HEIGHT - 110,isThereVertex8.clone() );

        boolean[] isThereVertex9 = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false};
        Edge edge9 = new Edge(graph, 65 , GameInfo.HEIGHT - 145,isThereVertex9.clone() );

        boolean[] isThereVertex10 = {false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge10 = new Edge(graph, 40 , GameInfo.HEIGHT - 150,isThereVertex10.clone() );

        boolean[] isThereVertex11 = {false,false,false,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false};
        Edge edge11 = new Edge(graph, 90 , GameInfo.HEIGHT - 150,isThereVertex11.clone() );

        boolean[] isThereVertex12 = {false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,true,false,false};
        Edge edge12 = new Edge(graph, 35 , GameInfo.HEIGHT - 155,isThereVertex12.clone() );

        boolean[] isThereVertex13 = {false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false};
        Edge edge13 = new Edge(graph, 95 , GameInfo.HEIGHT - 155,isThereVertex13.clone() );

        boolean[] isThereVertex14 = {false,false,false,false,false,false,false,false,false,true,false,true,false,false,false,false,false,false};
        Edge edge14 = new Edge(graph, 65 , GameInfo.HEIGHT - 150,isThereVertex14.clone() );

        boolean[] isThereVertex15 = {false,false,false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false};
        Edge edge15 = new Edge(graph, 65 , GameInfo.HEIGHT - 155,isThereVertex15.clone() );

        boolean[] isThereVertex16 = {false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge16 = new Edge(graph, 40 , GameInfo.HEIGHT - 75,isThereVertex16.clone() );

        boolean[] isThereVertex17 = {false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
        Edge edge17 = new Edge(graph, 35 , GameInfo.HEIGHT - 75,isThereVertex17.clone() );

        graph.addEdge(edge0);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        graph.addEdge(edge8);
        graph.addEdge(edge9);
        graph.addEdge(edge10);
        graph.addEdge(edge11);
        graph.addEdge(edge12);
        graph.addEdge(edge13);
        graph.addEdge(edge14);
        graph.addEdge(edge15);
        graph.addEdge(edge16);
        graph.addEdge(edge17);

        graph.assignDistances();

        System.out.println(graph);

        //Route route = new Route(edge15,edge13);
        
        System.out.println(graph.edges[9].yCoordinate);
        System.out.println(graph.edges[15].yCoordinate);
        //System.out.println(new Route(graph.edges[9],graph.edges[15]));
        System.out.println(graph.edges[9].xCoordinate);
        System.out.println(graph.edges[15].xCoordinate);


    }

    


}
