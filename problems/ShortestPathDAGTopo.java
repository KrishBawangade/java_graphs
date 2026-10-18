package problems;

import java.util.*;

public class ShortestPathDAGTopo {

    private static class Pair{
        int node;
        int dist;

        Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }

    public static int[] findShortestPath(int source, ArrayList<ArrayList<Pair>> adj){
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[adj.size()]; 

        for(int i=0; i<adj.size(); i++){
            if(!visited[i]){
                dfs(adj, visited, stack, i);
            }
        }

        int[] dist = new int[adj.size()];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        while(!stack.isEmpty()){
            Integer node = stack.pop();

            if(dist[node] == Integer.MAX_VALUE){
                continue;
            }

            for(Pair adjPair: adj.get(node)){
                int newDist = dist[node] + adjPair.dist;
                if(newDist<dist[adjPair.node]){
                    dist[adjPair.node] = newDist;
                }
            }
        }

        return dist;
    }

    private static void dfs(ArrayList<ArrayList<Pair>> adj, boolean[] visited, Stack<Integer> stack, int node){
        visited[node] = true;

        for(Pair adjPair: adj.get(node)){
            int adjNode = adjPair.node;

            if(!visited[adjNode]){
                dfs(adj, visited, stack, adjNode);
            }
        }

        stack.push(node);
    }

    public static void main(String[] args){
        int nodes = 7;
        int source = 6;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0; i<nodes; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).addAll(new ArrayList<>(Arrays.asList(new Pair(1, 2))));
        adj.get(1).addAll(new ArrayList<>(Arrays.asList(new Pair(3, 1))));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(new Pair(3, 3))));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList()));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(new Pair(0, 3), new Pair(2, 1))));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(new Pair(4, 1))));
        adj.get(6).addAll(new ArrayList<>(Arrays.asList(new Pair(4, 2), new Pair(5, 3))));

        System.out.println(Arrays.toString(findShortestPath(source, adj)));
    }
}
