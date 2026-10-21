package dijkstra;

import java.util.*;

public class DijkstraTreeSet {
    private static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static int[] findShortestPath(ArrayList<ArrayList<Pair>> adj, int src) {
        TreeSet<Pair> set = new TreeSet<>(
            (a, b) -> {
                int cmp = Integer.compare(a.dist, b.dist);

                if(cmp == 0){
                    return Integer.compare(a.node, b.node);
                }

                return cmp;
            }
        );

        int[] dist = new int[adj.size()];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        set.add(new Pair(src, 0));

        while (!set.isEmpty()) {
            Pair pair = set.pollFirst();

            for (Pair adjPair : adj.get(pair.node)) {
                int newDist = pair.dist + adjPair.dist;
                if (newDist < dist[adjPair.node]) {

                    if(dist[adjPair.node] != Integer.MAX_VALUE){
                        set.remove(new Pair(adjPair.node, dist[adjPair.node]));
                    }

                    dist[adjPair.node] = newDist;
                    set.add(new Pair(adjPair.node, newDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int nodes = 6;
        int src = 0;

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).addAll(new ArrayList<>(Arrays.asList(new Pair(1, 4), new Pair(2, 4))));
        adj.get(1).addAll(new ArrayList<>(Arrays.asList(new Pair(0, 4), new Pair(2, 2))));
        adj.get(2).addAll(new ArrayList<>(Arrays.asList(new Pair(0, 4), new Pair(1, 2),
                new Pair(3, 3), new Pair(4, 1), new Pair(5, 6))));
        adj.get(3).addAll(new ArrayList<>(Arrays.asList(new Pair(2, 3), new Pair(5, 2))));
        adj.get(4).addAll(new ArrayList<>(Arrays.asList(new Pair(2, 1), new Pair(5, 3))));
        adj.get(5).addAll(new ArrayList<>(Arrays.asList(new Pair(2, 6), new Pair(3, 2), new Pair(4, 3))));

        int[] res = findShortestPath(adj, src);

        System.out.println(Arrays.toString(res));
    }
}
