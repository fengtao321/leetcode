import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;

        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + '*' + word.substring(i + 1);
                ArrayList<String> list = dictionary.getOrDefault(key, new ArrayList<String>());
                list.add(word);
                dictionary.put(key, list);
            }
        }

        printHashMap(dictionary);

        HashSet<String> wordSet = new HashSet<>();
        wordSet.add(beginWord);
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        int counter = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            printQueue(queue);
            while (size > 0) {
                String word = queue.poll();
                if (Objects.equals(word, endWord)) {
                    return counter;
                }

                for (int i = 0; i < word.length(); i++) {
                    String key = word.substring(0, i) + '*' + word.substring(i + 1);
                    if (dictionary.containsKey(key)) {
                        ArrayList<String> list = dictionary.get(key);
                        for (int j = 0; j < list.size(); j++) {
                            String next = list.get(j);
                            if (!wordSet.contains(next)) {
                                wordSet.add(next);
                                queue.add(next);
                            }
                        }
                        dictionary.remove(key);
                    }
                }
                size--;
            }
            counter++;
        }

        return 0;
    }

    private void printHashMap(HashMap<String, ArrayList<String>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printArrayList(v);
            System.out.println("");
        });
    }

    private void printArrayList(ArrayList<String> object) {
        object.forEach(value -> System.out.print(value + "->"));
    }

    private void printQueue(ArrayDeque<String> object) {
        object.forEach(value -> System.out.print(value + "->"));
    }

}