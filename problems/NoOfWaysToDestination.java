package problems;

import java.util.*;

public class NoOfWaysToDestination {

    private static class Pair{
        int node;
        long dist;

        Pair(int node, long dist){
            this.node = node;
            this.dist = dist;
        }
    }

    public static int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] road: roads){
            ArrayList<Pair> list = adj.get(road[0]);

            list.add(new Pair(road[1], road[2]));

            ArrayList<Pair> list2 = adj.get(road[1]);

            list2.add(new Pair(road[0], road[2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Long.compare(a.dist, b.dist));
    
        long[] dist = new long[n];
        int[] ways = new int[n];

        final int MOD =  1_000_000_007;

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;

        pq.add(new Pair(0, 0));

        while(!pq.isEmpty()){
            Pair pair = pq.poll();

            if(pair.dist> dist[pair.node]){
                continue;
            }

            for(Pair adjPair: adj.get(pair.node)){
                long newDist = pair.dist+ adjPair.dist;

                if(newDist < dist[adjPair.node]){
                    dist[adjPair.node] = newDist;
                    ways[adjPair.node] = ways[pair.node];
                    pq.add(new Pair(adjPair.node, newDist));
                }else if(newDist == dist[adjPair.node]){
                    ways[adjPair.node] = (ways[adjPair.node] + ways[pair.node]) % MOD;
                }
            }
        }

        return ways[n-1];
    }

    public static void main(String[] args){
        int n = 7;
        int[][] roads = {
            {0,6,7}, {0,1,2}, {1,2,3}, {1,3,3}, {6,3,3},
            {3,5,1}, {6,5,1}, {2,5,1}, {0,4,5}, {4,6,2}
        };

        System.out.println(countPaths(n, roads));
    }
}
