package cycle_detection;

import java.util.*;

public class UndirectedBFS{

    private static class Pair{
        int node;
        int parent;

        Pair(int node, int parent){
            this.node = node;
            this.parent = parent;
        }
    }

    public static boolean checkCycle(int src, boolean[] visited, ArrayList<ArrayList<Integer>> adj){
        Queue<Pair> queue = new ArrayDeque<>();

        queue.add(new Pair(src, -1));
        visited[src] = true;

        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            int node = pair.node;
            int parent = pair.parent;

            for(int adjNode: adj.get(node)){
                if(!visited[adjNode]){
                    visited[adjNode] = true;
                    queue.add(new Pair(adjNode, node));
                }else{
                    if(parent!=adjNode){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];

        for(int i=1; i<adj.size(); i++){
            if(!visited[i]){
                if (checkCycle(i, visited, adj)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
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