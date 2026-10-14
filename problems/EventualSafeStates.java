package problems;

import java.util.*;

public class EventualSafeStates {
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        boolean[] pathVisited = new boolean[graph.length];
        boolean[] safe = new boolean[graph.length];

        List<Integer> safeNodes = new ArrayList<>();

        for(int i=0; i<graph.length; i++){
            if(!visited[i]){
                isSafe(graph, visited, pathVisited, safe, i);
            }
        }

        for(int i=0; i<safe.length; i++){
            if(safe[i]){
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    private static boolean isSafe(int[][] graph, boolean[] visited, boolean[] pathVisited, boolean[] safe, int node){
        visited[node] = true;
        pathVisited[node] = true;

        boolean isNodeSafe = true;

        for(int adjNode: graph[node]){
            if(!visited[adjNode]){
                if(!isSafe(graph, visited, pathVisited, safe, adjNode)){
                    isNodeSafe = false;
                }
            }else if(pathVisited[adjNode]){
                isNodeSafe = false;
            }else{
                if(!safe[adjNode]){
                    isNodeSafe = false;
                }
            }
        }

        pathVisited[node] = false;

        if(isNodeSafe){
            safe[node] = true;
        }

        return isNodeSafe;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {1,2},{2,3},{5},{0},{5},{},{}
        };

        System.out.println(eventualSafeNodes(graph));
    }
}
