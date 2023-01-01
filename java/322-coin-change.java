class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] ans = new int[amount + 1];
        ans[amount] = 1;

        for (int i = amount - 1; i >= 0; i--) {
            for (int coin : coins) {
                if ((i + coin) >= 0 && (i + coin) <= amount && ans[i + coin] > 0
                        && (ans[i] == 0 || ans[i] > (1 + ans[i + coin]))) {
                    ans[i] = 1 + ans[i + coin];
                }
            }
        }
        return ans[0] - 1;
    }
}