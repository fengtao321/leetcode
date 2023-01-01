class Solution {
    int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        this.grid = grid;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    int size = caculateSize(i, j);
                    maxSize = maxSize > size ? maxSize : size;
                }
            }
        }

        return maxSize;

    }

    private int caculateSize(int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
            return 0;

        grid[i][j] = 0;

        return 1 + caculateSize(i - 1, j) + caculateSize(i + 1, j) + caculateSize(i, j - 1) + caculateSize(i, j + 1);
    }
}