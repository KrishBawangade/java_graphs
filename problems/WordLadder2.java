package problems;

import java.util.*;

public class WordLadder2 {

    public static List<List<String>> findLadders(String beginWord, String endWord, String[] wordList) {
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(wordList));

        List<List<String>> wordLadders = new ArrayList<>();

        if (!wordSet.contains(endWord)) {
            return wordLadders;
        }

        Queue<String> queue = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();

        HashMap<String, Set<String>> map = new HashMap<>();

        map.put(beginWord, new HashSet<>());

        queue.add(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            Set<String> levelVisited = new HashSet<>();

            boolean hasFound = false;

            for (int i = 0; i < levelSize; i++) {
                String curr = queue.poll();

                char[] word = curr.toCharArray();

                for (int j = 0; j < word.length; j++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        word[j] = ch;

                        String str = new String(word);

                        if (str.equals(endWord)) {
                            hasFound = true;
                            Set<String> set = map.getOrDefault(str, new HashSet<>());
                            set.add(curr);
                            map.put(str, set);
                        } else if (wordSet.contains(str) && !visited.contains(str)) {
                            if (levelVisited.add(str)) {
                                queue.add(str);
                            }

                            Set<String> set = map.getOrDefault(str, new HashSet<>());
                            set.add(curr);
                            map.put(str, set);
                        }

                        word = curr.toCharArray();
                    }
                }
            }

            if (hasFound) {
                break;
            }

            visited.addAll(levelVisited);
        }

        backtrack(map, wordLadders, new ArrayList<>(), beginWord, endWord);

        return wordLadders;
    }

    private static void backtrack(HashMap<String, Set<String>> map, List<List<String>> wordLadders, List<String> path,
            String beginWord, String curr) {
        if (curr.equals(beginWord) && !path.isEmpty()) {
            path.add(0, curr);
            wordLadders.add(path);
            return;
        }

        List<String> list = new ArrayList<>(path);

        list.add(0, curr);

        for(String parent : map.getOrDefault(curr, new HashSet<>())) {
            backtrack(map, wordLadders, list, beginWord, parent);
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";

        String[] wordList = { "hot", "dot", "dog", "lot", "log", "cog" };

        System.out.println(findLadders(beginWord, endWord, wordList));
    }
}
