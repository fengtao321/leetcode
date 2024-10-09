import java.util.Arrays;

class Solution {
    int[] prices;
    int[][] dp;
    int isBuy = 0;
    int isSale = 1;
    int isCoolDown = 2;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.dp = new int[prices.length][3]; // buy, sell, cooldown
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        dfs(0, isBuy, true);
        dfs(0, isCoolDown, false);

        return Math.max(this.dp[0][isBuy], this.dp[0][isCoolDown]);
    }

    private int dfs(int i, int state, boolean hasStock) {
        if (i == prices.length)
            return 0;
        if (state != isCoolDown && dp[i][state] > -1)
            return dp[i][state];

        if (state == isBuy) {
            dp[i][isBuy] = Math.max(dfs(i + 1, isSale, true), dfs(i + 1, isCoolDown, true)) - prices[i];
        }

        if (state == isSale) {
            dp[i][isSale] = dfs(i + 1, isCoolDown, false) + prices[i];
        }

        if (state == isCoolDown) {
            if (hasStock) {
                dp[i][isCoolDown] = Math.max(dfs(i + 1, isSale, hasStock), dfs(i + 1, isCoolDown, hasStock));
            } else {
                dp[i][isCoolDown] = Math.max(dfs(i + 1, isBuy, hasStock), dfs(i + 1, isCoolDown, hasStock));
            }
        }

        return dp[i][state];
    }
}