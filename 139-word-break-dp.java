import java.util.List;

class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            if (!dp[i]) {
                continue;
            }

            for (String word : wordDict) {
                if ((i - word.length()) >= 0 && s.substring(i - word.length(), i).equals(word)) {
                    dp[i - word.length()] = true;
                    if (dp[0])
                        return true;
                }
            }

        }

        return false;
    }
}