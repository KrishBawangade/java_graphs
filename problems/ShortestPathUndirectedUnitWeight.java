package problems;

import java.util.*;

public class ShortestPathUndirectedUnitWeight {

    private static class Pair{
        int node;
        int dist;

        Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }

    public static int[] findShortestPath(int source, ArrayList<ArrayList<Integer>> adj){
        Queue<Pair> queue = new ArrayDeque<>();
        int[] dist = new int[adj.size()];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        queue.add(new Pair(source, 0));

        while(!queue.isEmpty()){
            Pair pair = queue.poll();

            for(int adjNode: adj.get(pair.node)){
                if(pair.dist+1 < dist[adjNode]){
                    dist[adjNode] = pair.dist+1;
                    queue.add(new Pair(adjNode, dist[adjNode]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args){
        int nodes = 9;
        int source = 0;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<nodes; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).addAll(new ArrayList<>(Arrays.asList(1,3)));
        adj.get(1).addAll(new ArrayList<>(Arrays.asList(0,2,3)));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(1,6)));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList(0,4)));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(3,5)));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(4,6)));
        adj.get(6).addAll(new ArrayList<>(Arrays.asList(2,5,7,8)));
        adj.get(7).addAll(new ArrayList<>(Arrays.asList(6,8)));
        adj.get(8).addAll(new ArrayList<>(Arrays.asList(6,7)));

        System.out.println(Arrays.toString(findShortestPath(source, adj)));
    }
}
