package problems;

public class NumberOfIslands {

    public static int numIslands(char[][] grid) {

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands =0;

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == '0' || visited[i][j]){
                    continue;
                }

                islands++;
                dfs(grid, visited, i, j);
            }
        }

        return islands;
    }

    private static void dfs(char[][] grid, boolean[][] visited, int i, int j){

        if(i<0 || i>=grid.length || j<0 || j>=grid[i].length){
            return;
        }

        if(grid[i][j] == '0' || visited[i][j]){
            return;
        }

        visited[i][j] = true;

        dfs(grid, visited, i-1, j);
        dfs(grid, visited, i+1, j);
        dfs(grid, visited, i, j-1);
        dfs(grid, visited, i, j+1);
    }

    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };

        System.out.println(numIslands(grid));
    }
}
