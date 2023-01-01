class Solution {
    public int checkRecord(int n) {
        int MOD = 1000000007;
        long[][][] dp = new long[n + 1][2][3]; // dp[i][A][L], A can be 0/1, L can be 0/1/2

        // init
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][1][0] = 1;

        // for testing
        // dp[2][0][0] = 2;
        // dp[2][0][1] = 1;
        // dp[2][0][2] = 1;

        // dp[2][1][0] = 3;
        // dp[2][1][1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            dp[i][0][1] = (dp[i - 1][0][0]) % MOD;
            dp[i][0][2] = (dp[i - 1][0][1]) % MOD;
            dp[i][1][0] = dp[i][0][0] + (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
            dp[i][1][1] = (dp[i - 1][1][0]) % MOD;
            dp[i][1][2] = (dp[i - 1][1][1]) % MOD;
        }

        return (int) ((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1]
                + dp[n][1][2]) % MOD);
    }
}