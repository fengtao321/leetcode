class Solution {
    int totalStep;

    public int uniquePathsIII(int[][] grid) {
        int startI = 0;
        int startJ = 0;
        totalStep = grid.length * grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    startI = i;
                    startJ = j;
                }
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                }

                if (grid[i][j] == -1) {
                    totalStep = totalStep - 1;
                }

            }
        }

        return backtrack(grid, startI, startJ, 1);
    }

    private int backtrack(int[][] grid, int i, int j, int step) {
        if (i == grid.length || i == -1 || j == -1 || j == grid[0].length || grid[i][j] < 0) {
            return 0;
        }
        if (grid[i][j] > 0) {
            return step == totalStep ? grid[i][j] : 0;
        }

        grid[i][j] = -1;
        int sum = backtrack(grid, i - 1, j, step + 1) + backtrack(grid, i + 1, j, step + 1)
                + backtrack(grid, i, j - 1, step + 1) + backtrack(grid, i, j + 1, step + 1);

        grid[i][j] = 0;
        return sum;
    }
}