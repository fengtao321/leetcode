import java.util.Arrays;

class Solution {
    int mod = 1000000007;
    int[][] dp = new int[3002][1001]; // i is startPosition+1001, j is k;

    public int numberOfWays(int startPos, int endPos, int k) {
        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }

        return dp(startPos, endPos, k);
    }

    public int dp(int startPos, int endPos, int k) {
        if (k == 0) {
            if (startPos == endPos) {
                return 1;
            }

            return 0;
        }
        int i = startPos + 1001;
        int j = k;
        if (dp[i][j] != -1) {
            return dp[i][k];
        }

        k--;
        int ans = (numberOfWays(startPos + 1, endPos, k) + numberOfWays(startPos - 1, endPos, k)) % mod;
        dp[i][j] = ans;
        return ans;
    }
}