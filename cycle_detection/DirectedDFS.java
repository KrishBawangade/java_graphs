package cycle_detection;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectedDFS {

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        boolean[] pathVisited = new boolean[adj.size()];

        for(int i=1; i<adj.size(); i++){
            if(!visited[i]){
                if(dfs(adj, visited, pathVisited, i)) return true;
            }
        }

        return false;
    }

    private static boolean dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited, int node){
        
        visited[node] = true;
        pathVisited[node] = true;

        for(int adjNode: adj.get(node)){
            if(!visited[adjNode]){
                if(dfs(adj, visited, pathVisited, adjNode)) return true;
            }else if(pathVisited[adjNode]){
                return true;
            }
        }

        pathVisited[node] = false;

        return false;
    }

    public static void main(String[] args) {
        int nodes = 10;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<nodes+1; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).addAll(new ArrayList<>(Arrays.asList(2)));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(3)));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList(4,7)));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(5)));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(6)));
        adj.get(7).addAll(new ArrayList<>(Arrays.asList(5)));
        adj.get(8).addAll(new ArrayList<>(Arrays.asList(9)));
        adj.get(9).addAll(new ArrayList<>(Arrays.asList(10)));
        adj.get(10).addAll(new ArrayList<>(Arrays.asList(8)));

        System.out.println(isCycle(adj));
    }
}
