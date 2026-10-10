package traversal;

import java.util.*;

public class BFSTraversal {

    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[V];
        ArrayList<Integer> bfs = new ArrayList<>();

        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            int node = queue.poll();
            bfs.add(node);

            for(int adjNode: adj.get(node)){
                if(visited[adjNode]){
                    continue;
                }

                queue.add(adjNode);
                visited[adjNode] = true;
            }
        }

        return bfs;
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

        System.out.println(bfsOfGraph(V, adj));
    }
}
