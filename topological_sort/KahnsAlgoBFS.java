package topological_sort;

import java.util.*;

public class KahnsAlgoBFS {

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj){
        int[] indeg = new int[adj.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();

        //finding in-degree for each node
        for(int i=0; i<adj.size(); i++){
            for(int adjNode: adj.get(i)){
                indeg[adjNode]++;
            }
        }

        for(int i=0; i<adj.size(); i++){
            if(indeg[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int node = queue.poll();
            
            for(int adjNode: adj.get(node)){
                indeg[adjNode]--;

                if(indeg[adjNode] == 0){
                    queue.add(adjNode);
                }
            }

            res.add(node);
        }
        
        // return empty list if the cycle is detected
        return res.size()!=adj.size()? new ArrayList<>(): res;
    }

    public static void main(String[] args){
        int nodes = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<nodes; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).addAll(new ArrayList<>(Arrays.asList()));
        adj.get(1).addAll(new ArrayList<>(Arrays.asList()));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(3)));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList(1)));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(0,1)));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(0,2)));

        System.out.println(topologicalSort(adj));
    }
}
