package problems;

public class NumberOfEnclaves {

    public static int numEnclaves(int[][] grid) {
        int enclaves = 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            int j = 0;
            if (grid[i][j] == 1 && !visited[i][j]) {
                dfs(grid, visited, i, j);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            int j = grid[i].length - 1;
            if (grid[i][j] == 1 && !visited[i][j]) {
                dfs(grid, visited, i, j);
            }
        }

        for (int j = 0; j < grid[0].length; j++) {
            int i = 0;
            if (grid[i][j] == 1 && !visited[i][j]) {
                dfs(grid, visited, i, j);
            }
        }

        for (int j = 0; j < grid[0].length; j++) {
            int i = grid.length - 1;
            if (grid[i][j] == 1 && !visited[i][j]) {
                dfs(grid, visited, i, j);
            }
        }

        for(int i=1; i<grid.length-1; i++){
            for(int j=1; j<grid[i].length-1; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    enclaves++;
                }
            }
        }

        return enclaves;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j) {

        if (i < 0 ||
                i >= grid.length || j < 0 ||
                j >= grid[i].length ||
                grid[i][j] == 0 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        dfs(grid, visited, i-1, j);
        dfs(grid, visited, i+1, j);
        dfs(grid, visited, i, j-1);
        dfs(grid, visited, i, j+1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 }
        };

        System.out.println(numEnclaves(grid));
    }
}
