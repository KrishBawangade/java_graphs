package topological_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TopologicalSortDFS {

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> sortedOrder = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<adj.size(); i++){
            if(!visited[i]){
                dfs(adj, visited, stack, i);
            }
        }

        while(!stack.isEmpty()){
            sortedOrder.add(stack.pop());
        }

        return sortedOrder;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack, int node){
        visited[node] = true;

        for(int adjNode: adj.get(node)){
            if(!visited[adjNode]){
                dfs(adj, visited, stack, adjNode);
            }
        }

        stack.push(node);
    }

    public static void main(String[] args) {
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
