package cycle_detection;

import java.util.ArrayList;
import java.util.Arrays;

public class UndirectedDFS {

    private static boolean dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int node, int parent){
        visited[node] = true;

        for(int adjNode: adj.get(node)){
            if(!visited[adjNode]){
                if (dfs(adj, visited, adjNode, node)) return true;
            }else if(parent != adjNode){
                return true;
            }
        }

        return false;
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];

        for(int i=1; i<adj.size(); i++){
            if(!visited[i]){
                if(dfs(adj, visited, i, -1)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args){
        int nodes = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<nodes+1; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).addAll(new ArrayList<>(Arrays.asList(2,3)));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(1,5)));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList(1,4,6)));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(3)));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(2,7)));
        adj.get(6).addAll(new ArrayList<>(Arrays.asList(3,7)));
        adj.get(7).addAll(new ArrayList<>(Arrays.asList(5,6)));

        System.out.println(isCycle(adj));
    }
}
