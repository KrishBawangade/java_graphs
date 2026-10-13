package problems;

import java.util.*;

public class NumberOfDistinctIslands {

    private static class Pair{
        int i;
        int j;

        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }

        @Override 
        public boolean equals(Object obj){
            if(this == obj) return true;

            if(!(obj instanceof Pair)) return false;

            Pair other = (Pair) obj;

            return i == other.i && j == other.j;
        }

        @Override 
        public int hashCode(){
            return Objects.hash(i, j);
        }
    }

    public static int numDistinctIslands(int[][] grid){

        HashSet<HashSet<Pair>> distinctIslands = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    HashSet<Pair> island = bfs(grid, visited, new Pair(i, j));

                    distinctIslands.add(island);
                }
            }
        }

        return distinctIslands.size();
    }

    private static HashSet<Pair> bfs(int[][] grid, boolean[][] visited, Pair src){
        Queue<Pair> queue = new ArrayDeque<>();

        HashSet<Pair> set = new HashSet<>();

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        queue.add(src);
        visited[src.i][src.j] = true;
        set.add(new Pair(0,0));

        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            int i = pair.i;
            int j = pair.j;

            for(int[] direction: directions){
                int nR = direction[0] + i;
                int nC = direction[1] + j;

                if(nR>=0 && nR<grid.length && nC>=0 && nC<grid[i].length && grid[nR][nC] == 1 && !visited[nR][nC]){
                    queue.add(new Pair(nR, nC));
                    set.add(new Pair(src.i-nR, src.j-nC));
                    visited[nR][nC] = true;
                }
            }

        }

        return set;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1,1,0,1,1},
            {1,0,0,0,0},
            {0,0,0,1,1},
            {1,1,0,1,0}
        };

        System.out.println(numDistinctIslands(grid));
    }
}
