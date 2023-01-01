class Solution {
    int[][] obstacleGrid;
    int[][] dp;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.obstacleGrid = obstacleGrid;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        dp = new int[m][n];
        dp[m - 1][n - 1] = 1;

        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i == obstacleGrid.length || j == obstacleGrid[0].length || obstacleGrid[i][j] == 1)
            return 0;
        if (dp[i][j] > 0)
            return dp[i][j];

        return dp[i][j] = dfs(i + 1, j) + dfs(i, j + 1);

    }
}