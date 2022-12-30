import java.util.Arrays;

class Solution {
    int[] prices;
    int[][] dp;
    int isBuy = 0;
    int isNotBuy = 1;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.dp = new int[prices.length][2]; // buyStock or not
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        return dfs(0, isBuy);
    }

    private int dfs(int i, int state) {
        if (i >= prices.length)
            return 0;
        if (dp[i][state] > -1)
            return dp[i][state];

        int coolDown = dfs(i + 1, state);
        if (state == isBuy) {
            dp[i][state] = Math.max(dfs(i + 1, isNotBuy) - prices[i], coolDown);
        } else {
            dp[i][state] = Math.max(dfs(i + 2, isBuy) + prices[i], coolDown);
        }

        return dp[i][state];
    }
}