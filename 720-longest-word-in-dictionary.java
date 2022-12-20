import java.util.Arrays;
import java.util.HashMap;

class Solution {
    int[] dp;
    HashMap<String, Integer> map;

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        dp = new int[words.length];
        map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        int counter = 0;
        String ans = "";

        for (String word : words) {
            int depth = getDepth(word);
            if (depth > counter || (depth == counter && word.compareTo(ans) < 0)) {
                counter = depth;
                ans = word;
            }
        }

        return counter > 0 ? ans : "";
    }

    private int getDepth(String word) {
        if (word.length() == 0)
            return 0;

        if (!map.containsKey(word))
            return Integer.MIN_VALUE;

        if (dp[map.get(word)] > 0)
            return dp[map.get(word)];

        String pre = word.substring(0, word.length() - 1);
        dp[map.get(word)] = getDepth(pre) + 1;
        return dp[map.get(word)];
    }

}
