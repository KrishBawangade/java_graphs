package bipartite_graph;

import java.util.*;

public class BipartiteGraphBFS {
    public static boolean isBipartite(ArrayList<ArrayList<Integer>> adj){

        int[] color = new int[adj.size()];

        for(int i=1; i<adj.size(); i++){
            if(color[i] == 0){
                if(!bfs(adj, color, i)) return false;
            }
        }

        return true;
    }

    private static boolean bfs(ArrayList<ArrayList<Integer>> adj, int[] color, int src){
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(src);
        color[src] = 1;

        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int adjNode: adj.get(node)){
                if(color[node] == color[adjNode]){
                    return false;
                }else if(color[adjNode] == 0){
                    color[adjNode] = -color[node];
                    queue.add(adjNode);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int nodes = 8;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<nodes+1; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).addAll(new ArrayList<>(Arrays.asList(2)));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(1,3,6)));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList(2,4)));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(3,5,7)));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(4,6)));
        adj.get(6).addAll(new ArrayList<>(Arrays.asList(2,5)));
        adj.get(7).addAll(new ArrayList<>(Arrays.asList(4,8)));
        adj.get(8).addAll(new ArrayList<>(Arrays.asList(7)));

        System.out.println(isBipartite(adj));
    }
}
