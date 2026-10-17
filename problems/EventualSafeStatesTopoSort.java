package problems;

import java.util.*;

public class EventualSafeStatesTopoSort {

    public static ArrayList<Integer> eventualSafeNodes(int[][] graph){
        ArrayList<ArrayList<Integer>> reverseAdj = new ArrayList<>();

        for(int i=0; i<graph.length; i++){
            reverseAdj.add(new ArrayList<>());
        }
        
        for(int i=0; i<graph.length; i++){
            for(int adjNode: graph[i]){
                reverseAdj.get(adjNode).add(i);
            }
        }

        ArrayList<Integer> res = topologicalSort(reverseAdj);
        Collections.sort(res);
        return res;
    }

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
        
        return res;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {1,2},{2,3},{5},{0},{5},{},{}
        };

        System.out.println(eventualSafeNodes(graph));
    }
}
