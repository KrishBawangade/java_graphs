package problems;

import java.util.*;

public class MinimumMultiplicationsReachEnd {

    public static int minimumMultiplications(int[] arr, int start, int end){
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[100000];

        queue.add(start);

        visited[start] = true;

        int steps = 0;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i=0; i<size; i++){

                int ele = queue.poll();

                if(ele == end){
                    return steps;
                }
    
                for(int num: arr){
                    int newStart = (ele * num) % 100000;

                    if (!visited[newStart]) {

                        visited[newStart] = true;
                        queue.add(newStart);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args){
        int[] arr = {2,5,7};
        int start = 3;
        int end = 30;

        System.out.println(minimumMultiplications(arr, start, end));
    }
}
