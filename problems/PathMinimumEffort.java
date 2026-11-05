package problems;

import java.util.*;

public class PathMinimumEffort {

    private static class Node {
        int row;
        int col;
        int dist;

        Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static int minimumEffortPath(int[][] heights) {

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));

        int[][] dist = new int[heights.length][heights[0].length];

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;

        pq.add(new Node(0, 0, 0));

        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.dist > dist[node.row][node.col]) {
                continue;
            }
            
            if (node.row == heights.length - 1 && node.col == heights[0].length - 1) {
                return node.dist;
            }


            for (int[] dir : directions) {
                int nR = node.row + dir[0];
                int nC = node.col + dir[1];

                if (nR >= 0 && nR < heights.length && nC >= 0 && nC < heights[0].length) {
                    int newDist = Math.max(
                            node.dist,
                            Math.abs(heights[nR][nC] - heights[node.row][node.col]));

                    if (newDist < dist[nR][nC]) {
                        pq.add(new Node(nR, nC, newDist));
                        dist[nR][nC] = newDist;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] heights = { { 1, 2, 3 }, { 3, 8, 4 }, { 5, 3, 5 } };

        System.out.println(minimumEffortPath(heights));
    }
}
