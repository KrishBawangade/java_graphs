package problems;

import java.util.*;

public class SurroundedRegions{

    private static class Pair{
        int i;
        int j;

        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void solve(char[][] board) {
        boolean[][] visited= new boolean[board.length][board[0].length];

        Queue<Pair> queue = new ArrayDeque<>();

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == 'O' && isOnEdge(board, i, j)){
                    queue.add(new Pair(i, j));
                    visited[i][j]= true;
                }
            }
        }

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            int i = pair.i;
            int j = pair.j;

            for(int[] direction: directions){
                int nR = direction[0] + i;
                int nC = direction[1] +j;

                if(nR>=0 && nR<board.length && nC>=0 && nC<board[i].length && board[nR][nC] == 'O' && !visited[nR][nC]){
                    queue.add(new Pair(nR, nC));
                    visited[nR][nC] = true;
                }
            }
        }

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == 'O' && !visited[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static boolean isOnEdge(char[][] board, int i, int j){
        if(i==0 || i==board.length-1 || j==0 || j==board[i].length-1){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = {
            {'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}
        };

        solve(board);

        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
    }
}