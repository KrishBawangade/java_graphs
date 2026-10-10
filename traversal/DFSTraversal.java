package traversal;

import java.util.ArrayList;

public class DFSTraversal {

    public static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[V];

        dfs(0, adj, res, visited);

        return res;
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> res, boolean[] visited){
        visited[node] = true;
        res.add(node);

        for(int adjNode: adj.get(node)){
            if(visited[adjNode]){
                continue;
            }
            
            dfs(adjNode, adj, res, visited);
        }
    }   

    public static void main(String[] args){
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<Integer>());
        }

        // for node 0
        adj.get(0).add(1);
        adj.get(0).add(2);

        // for node 1
        adj.get(1).add(0);
        adj.get(1).add(3);
        adj.get(1).add(4);

        // for node 2
        adj.get(2).add(0);
        adj.get(2).add(5);

        // for node 3
        adj.get(3).add(1);

        // for node 4
        adj.get(4).add(1);

        // for node 5
        adj.get(5).add(2);

        System.out.println(dfsOfGraph(V, adj));
    }
}
