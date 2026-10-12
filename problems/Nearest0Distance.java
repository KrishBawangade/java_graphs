package problems;

import java.util.*;

public class Nearest0Distance {

    private static class Pair{
        int i;
        int j;

        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static int[][] updateMatrix(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];

        Queue<Pair> queue = new ArrayDeque<>();

        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[i].length; j++){
                if(mat[i][j] == 0){
                    res[i][j] =0;
                    queue.add(new Pair(i, j));
                }else{
                    res[i][j] = -1;
                }
            }
        }

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while(!queue.isEmpty()){
            Pair pair = queue.poll();

            int i = pair.i;
            int j = pair.j;
            

            for(int[] direction: directions){
                int nR = i+direction[0];
                int nC = j+direction[1];

                if(nR>=0 && nR<mat.length && nC>=0 && nC<mat[i].length && res[nR][nC] == -1){
                    res[nR][nC] = res[i][j]+1;
                    queue.add(new Pair(nR, nC));
                }
            }

        }

        return res;
    }

    public static void main(String[] args){
        int[][] mat = {
            {0,0,0},{0,1,0},{1,1,1}
        };

        int[][] res = updateMatrix(mat);
        
        for(int i=0; i<res.length; i++){
            for(int j=0; j<res[i].length; j++){
                System.out.print(res[i][j] + " ");
            }

            System.out.println();
        }

    }
}
