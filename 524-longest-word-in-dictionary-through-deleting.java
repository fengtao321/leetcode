import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        PriorityQueue<String> queue = new PriorityQueue<>((s1, s2) -> {
            if (s2.length() - s1.length() > 0 || (s2.length() == s1.length() && s1.compareTo(s2) > 0))
                return 1;
            return -1;
        });

        for (String str : dictionary) {
            checkString(str, s, queue, 0, 0);
        }
        System.out.println(queue);
        return queue.size() > 0 ? queue.peek() : "";
    }

    private void checkString(String str, String target, PriorityQueue<String> queue, int i, int j) {
        if (str.length() - i > target.length() - j)
            return;

        if (i == str.length()) {
            System.out.println("Adding to queue: " + str);
            queue.add(str);
            return;
        }

        if (str.charAt(i) == target.charAt(j)) {
            checkString(str, target, queue, i + 1, j + 1);
            return;
        }

        checkString(str, target, queue, i, j + 1);
        return;

    }

    private void printHashMap(HashMap<Character, Integer> map) {
        for (Character key : map.keySet()) {
            System.out.print(key.toString() + " " + map.get(key) + "->");
        }
        System.out.println("");
    }
}