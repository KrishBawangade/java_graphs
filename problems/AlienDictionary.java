package problems;

import java.util.*;

public class AlienDictionary {

    public static String findOrder(int n, int k, String[] dict){
        int[][] adj = new int[k][k];
        int[] indeg = new int[k];

        for(int i=0; i<n-1; i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
            int m=0;

            int len = Math.min(s1.length(), s2.length());

            while(m<len && s1.charAt(m) == s2.charAt(m)){
                m++;
            }

            if(m<len){
                char ch1 = s1.charAt(m);
                char ch2 = s2.charAt(m);

                if(adj[ch1-'a'][ch2-'a'] != 1){
                    adj[ch1-'a'][ch2-'a'] = 1;
                    indeg[ch2-'a']++;
                }

            }else if(s1.length()>s2.length()){
                return ""; // invalid dictionary
            }
        }

        StringBuilder res = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();

        for(int j=0; j<k; j++){
            if(indeg[j] == 0){
                queue.add(j);
            }
        }

        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int idx = 0; idx<k; idx++){
                if(adj[node][idx] == 1){
                    indeg[idx]--;

                    if(indeg[idx] == 0){
                        queue.add(idx);
                    }
                }
            }

            res.append((char) ('a' + node));
        }

        // Cycle detected
        return res.length() == k ? res.toString() : "";
    }

    public static void main(String[] args) {
        int n = 5;
        int k =4;

        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};

        System.out.println(findOrder(n, k, dict));
    }
}
