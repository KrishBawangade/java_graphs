package problems;

import java.util.*;

public class CheapestFlightsKStops {

    private static class Node {
        int city;
        int cost;
        int k;

        Node(int city, int cost, int k) {
            this.city = city;
            this.cost = cost;
            this.k = k;
        }
    }

    private static class Pair {
        int city;
        int cost;

        Pair(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(i, new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            int[] flight = flights[i];

            ArrayList<Pair> list = adj.get(flight[0]);
            list.add(new Pair(flight[1], flight[2]));
        }

        int[][] cost = new int[n][k + 2];

        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        cost[src][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        pq.add(new Node(src, 0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.city == dst) {
                return node.cost;
            }

            if (node.k == k + 1) {
                continue;
            }

            if (node.cost > cost[node.city][node.k + 1]) {
                continue;
            }

            for (Pair adjPair : adj.get(node.city)) {
                int newCost = node.cost + adjPair.cost;
                int newK = node.k + 1;

                if (newCost < cost[adjPair.city][newK]) {
                    cost[adjPair.city][newK] = newCost;
                    pq.add(new Node(adjPair.city, newCost, node.k + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 4;

        int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
        int src = 0;
        int dest = 3;
        int k = 1;

        System.out.println(findCheapestPrice(n, flights, src, dest, k));
    }
}
