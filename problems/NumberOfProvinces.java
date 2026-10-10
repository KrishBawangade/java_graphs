package problems;

public class NumberOfProvinces {
    public static int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];

        int provinces = 0;

        for (int i = 0; i < isConnected.length; i++) {
            if(!visited[i]){
                provinces++;

                dfs(isConnected, visited, i);
            }
        }

        return provinces;
    }

    private static void dfs(int[][] isConnected, boolean[] isVisited, int i){
        isVisited[i] = true;

        for(int j=0; j<isConnected.length; j++){
            if(!isVisited[j] && isConnected[i][j] == 1){
                dfs(isConnected, isVisited, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {
                { 1, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 1 } };

        System.out.println(findCircleNum(isConnected));
    }
}
