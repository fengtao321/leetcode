import java.util.Arrays;

class Solution {
    int[][] dp;

    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        dp[m - 1][n - 1] = 1;

        return dp(0, 0);
    }

    private int dp(int i, int j) {
        if (dp[i][j] > -1)
            return dp[i][j];

        int sum = 0;

        if (i < dp.length - 1) {
            sum = sum + dp(i + 1, j);
        }

        if (j < dp[0].length - 1) {
            sum = sum + dp(i, j + 1);
        }

        dp[i][j] = sum;
        return sum;
    }
}