package bellman_ford;

import java.util.*;

public class BellmanFord {
    private static class Edge {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static int[] findShortestPath(ArrayList<Edge> edges, int n, int src) {
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;

            for (Edge edge : edges) {
                if (dist[edge.u] == Integer.MAX_VALUE) {
                    continue;
                }

                int newDist = dist[edge.u] + edge.w;
                if (newDist < dist[edge.v]) {
                    dist[edge.v] = newDist;
                    updated = true;
                }
            }

            if(!updated){
                break;
            }
        }

        // negative cycle detection
        for (Edge edge : edges) {
            if (dist[edge.u] == Integer.MAX_VALUE) {
                continue;
            }

            int newDist = dist[edge.u] + edge.w;
            if (newDist < dist[edge.v]) {
                return new int[n];
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int nodes = 6;
        int src = 0;

        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 5, -3));
        edges.add(new Edge(1, 2, -2));
        edges.add(new Edge(5, 3, 1));
        edges.add(new Edge(3, 2, 6));
        edges.add(new Edge(3, 4, -2));
        edges.add(new Edge(2, 4, 3));

        int[] res = findShortestPath(edges, nodes, src);

        System.out.println(Arrays.toString(res));
    }
}
