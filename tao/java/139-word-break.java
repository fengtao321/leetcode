import java.util.List;

class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        dp[s.length() - 1] = true;

        for (int i = s.length() - 2; i >= 0; i--) {
            if (dp[i]) {
                break;
            }

            for (String word : wordDict) {
                if ((i + word.length()) < s.length() && s.substring(i, i + word.length()).equals(word)) {
                    dp[i] = dp[i + word.length()];
                }
            }

        }

        return dp[0];
    }
}