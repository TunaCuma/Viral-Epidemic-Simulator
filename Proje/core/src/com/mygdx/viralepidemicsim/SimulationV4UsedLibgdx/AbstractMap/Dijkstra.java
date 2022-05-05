package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.AbstractMap;

import java.util.Arrays;

import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;

public class Dijkstra {

    public static void dijkstra(DirectedGraph graph, int source) {
      int count = graph.numberOfEdges;

      boolean[] visitedVertex = new boolean[count];
      int[] distance = new int[count];
      for (int i = 0; i < count; i++) {
        visitedVertex[i] = false;
        distance[i] = Integer.MAX_VALUE;
      }
  
      // Distance of self loop is zero
      distance[source] = 0;
      for (int i = 0; i < count; i++) {
  
        // Update the distance between neighbouring vertex and source vertex
        int u = findMinDistance(distance, visitedVertex);
        visitedVertex[u] = true;
  
        // Update all the neighbouring vertex distances
        for (int v = 0; v < count; v++) {
          if (!visitedVertex[v] && graph.edges[u].adjacenyDistanceList[v] != 0 && (distance[u] + graph.edges[u].adjacenyDistanceList[v] < distance[v])) {
            distance[v] = distance[u] + graph.edges[u].adjacenyDistanceList[v];
          }
        }
      }
      for (int i = 0; i < distance.length; i++) {
        System.out.println(String.format("Distance from %s to %s is %s", source, i, distance[i]));
      }
  
    }
  
    // Finding the minimum distance
    private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
      int minDistance = Integer.MAX_VALUE;
      int minDistanceVertex = -1;
      for (int i = 0; i < distance.length; i++) {
        if (!visitedVertex[i] && distance[i] < minDistance) {
          minDistance = distance[i];
          minDistanceVertex = i;
        }
      }
      
      return minDistanceVertex;
    }
  
    public static void main(String[] args) {
      DirectedGraph graph = createGraph();

      //int graph[][] = new int[][] { { 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0, 3, 0 }, { 1, 2, 0, 1, 3, 0, 0 },
      //    { 2, 0, 1, 0, 0, 0, 1 }, { 0, 0, 3, 0, 0, 2, 0 }, { 0, 3, 0, 0, 2, 0, 1 }, { 0, 0, 0, 1, 0, 1, 0 } };
      Dijkstra.dijkstra(graph, 0);
    }

    public static DirectedGraph createGraph(){
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
        return graph;
    }
}

