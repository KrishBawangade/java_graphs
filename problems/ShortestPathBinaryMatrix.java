package problems;

import java.util.*;

public class ShortestPathBinaryMatrix {

    private static class Pair{
        int row;
        int col;

        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {

        if(grid[0][0] == 1 || grid[grid.length-1][grid[0].length-1] == 1)
        return -1;

        int pathLen = 0;

        Queue<Pair> queue = new ArrayDeque<>();

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        queue.add(new Pair(0,0));
        visited[0][0] = true;

        while(!queue.isEmpty()){

            int size = queue.size();
            pathLen++;

            for(int i=0; i<size; i++){

                Pair pair = queue.poll();

                if(pair.row == grid.length-1 && pair.col == grid[pair.row].length-1){
                    return pathLen;
                }
    
                for(int[] dir: directions){
                    int nR = pair.row+dir[0];
                    int nC = pair.col+dir[1];
    
                    if(nR>=0 && nR<grid.length && nC>=0 && nC<grid[nR].length && grid[nR][nC] == 0 && !visited[nR][nC]){
                        queue.add(new Pair(nR, nC));
                        visited[nR][nC] = true;
                    }
                }
            }
        } 

        return -1;
    }

    public static void main(String[] args){
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};

        System.out.println(shortestPathBinaryMatrix(grid));
    }
}
