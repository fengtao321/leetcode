class Solution {
    int[] toppingCosts;
    int diffM = Integer.MAX_VALUE;
    int ans = Integer.MAX_VALUE;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        this.toppingCosts = toppingCosts;
        for (int baseCost : baseCosts) {
            dfs(baseCost, target, 0);
            if (ans == target)
                return ans;
        }
        return ans;
    }

    private void dfs(int cost, int target, int toppingIndex) {
        if (cost == target) {
            diffM = 0;
            ans = cost;
            return;
        }

        int diff = Math.abs(cost - target);
        if (diff < diffM || (diff == diffM && cost < target)) {
            diffM = diff;
            ans = cost;
        }

        if ((toppingIndex == toppingCosts.length) || (diff >= diffM && cost >= target))
            return;

        dfs(cost + toppingCosts[toppingIndex] * 2, target, toppingIndex + 1);
        dfs(cost + toppingCosts[toppingIndex], target, toppingIndex + 1);
        dfs(cost, target, toppingIndex + 1);
    }
}