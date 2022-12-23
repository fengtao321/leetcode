class Solution {
    int m;
    int n;
    int originalTarget;
    int minD = (int) 10e8;

    public int minimizeTheDifference(int[][] mat, int target) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.originalTarget = target;
        Integer[][] dp = new Integer[m][4901];
        return minDiff(mat, 0, 0, dp);
    }

    private int minDiff(int[][] mat, int sum, int row, Integer[][] dp) {
        if (row == m) {
            minD = Math.min(minD, Math.abs(sum - originalTarget));
            return Math.abs(sum - originalTarget);
        }

        if (dp[row][sum] != null)
            return dp[row][sum];

        int ans = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            int newSum = sum + mat[row][col];
            if (newSum > originalTarget + minD)
                continue;
            ans = Math.min(ans, minDiff(mat, newSum, row + 1, dp));
        }

        return dp[row][sum] = ans;

    }
}