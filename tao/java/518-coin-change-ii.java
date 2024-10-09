import java.util.Arrays;

class Solution {
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int[][] dp = new int[coins.length][amount + 1];

        for (int i = 0; i < coins.length; i++) {
            dp[i][amount] = 1;
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            int coin = coins[i];
            for (int j = amount - 1; j >= 0; j--) {
                if (j + coin <= amount) {
                    dp[i][j] += dp[i][j + coin];
                }
                if (i + 1 < coins.length) {
                    dp[i][j] += dp[i + 1][j];
                }
            }
        }

        return dp[0][0];

    }
}