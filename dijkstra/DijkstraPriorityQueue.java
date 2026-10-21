package dijkstra;

import java.util.*;

public class DijkstraPriorityQueue {

    private static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static int[] findShortestPath(ArrayList<ArrayList<Pair>> adj, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));

        int[] dist = new int[adj.size()];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();

            if (pair.dist > dist[pair.node]) {
                continue;
            }

            for (Pair adjPair : adj.get(pair.node)) {
                int newDist = pair.dist + adjPair.dist;
                if (newDist < dist[adjPair.node]) {
                    dist[adjPair.node] = newDist;
                    pq.add(new Pair(adjPair.node, newDist));
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
