class Solution {
    char[][] grid;
    int rowNum;
    int colNum;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        int totalIslands = 0;
        rowNum = grid.length;
        colNum = grid[0].length;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == '1') {
                    updateIsland(i, j);
                    totalIslands++;
                    // printGrid();
                }
            }
        }

        return totalIslands;

    }

    private void printGrid() {
        System.out.println("========================");
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                System.out.print(grid[i][j] + "->");
            }
            System.out.println("");
        }
    }

    private void updateIsland(int i, int j) {
        if (i < 0 || j < 0 || i >= rowNum || j >= colNum || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        updateIsland(i - 1, j);
        updateIsland(i + 1, j);
        updateIsland(i, j - 1);
        updateIsland(i, j + 1);
    }
}