import java.util.Arrays;
import java.util.HashSet;

class Solution {

    public int numDecodings(String s) {
        int l = s.length();
        int[] dp = new int[l];

        Character[] arr = { '0', '1', '2', '3', '4', '5', '6' };
        HashSet<Character> set = new HashSet<>(Arrays.asList(arr));

        dp[0] = 1; // dummy node, last is one
        dp[1] = s.charAt(1) == '0' ? 0 : 1;

        for (int i = l - 2; i >= 0; i--) {
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);

            if (next != '0') { // curr, next, not [curr+next]
                dp[i] = dp[i + 1];
            }

            // curr+next
            if (curr == '1' || (curr == '2' && set.contains(next))) {
                dp[i] += dp[i + 2];
            }
        }

        return dp[l];
    }
}