import java.util.Arrays;

class Solution {
    char[][] grid;
    int[][] record;
    int totalIslands = 0;
    int counter = 0;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.record = new int[grid.length][grid[0].length];
        for (int i = 0; i < record.length; i++) {
            for (int j = 0; j < record[0].length; j++) {
                this.record[i][j] = 0;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                updateIsland(i, j);
            }
        }

        // for (int i = 0; i < record.length; i++) {
        // for (int j = 0; j < record[0].length; j++) {
        // System.out.print(this.record[i][j] + "->");
        // }

        // System.out.println("");
        // }

        return totalIslands;

    }

    private void updateIsland(int i, int j) {
        if (grid[i][j] == '0')
            return;

        boolean condition1 = (i == 0 && j == 0);
        boolean condition2 = ((i - 1) < 0 && (j - 1) >= 0 && grid[i][j - 1] == '0');
        boolean condition3 = ((j - 1) < 0 && (i - 1) >= 0 && grid[i - 1][j] == '0');
        boolean condition4 = ((j - 1) >= 0 && (i - 1) >= 0 && grid[i - 1][j] == '0' && grid[i][j - 1] == '0');

        if (condition1 || condition2 || condition3 || condition4) {
            totalIslands = totalIslands + 1;
            counter = counter + 1;
            record[i][j] = counter;
            return;
        }

        if ((i - 1) < 0 || grid[i - 1][j] == '0') {
            record[i][j] = record[i][j - 1];
            return;

        }

        if ((j - 1) < 0 || grid[i][j - 1] == '0') {
            record[i][j] = record[i - 1][j];
            return;
        }

        if (record[i - 1][j] == record[i][j - 1]) {
            record[i][j] = record[i - 1][j];
            return;
        }

        totalIslands = totalIslands - 1;
        record[i][j] = record[i][j - 1];
        traceBackIsland(i, j - 2, record[i - 1][j], record[i][j]);
        if (i > 0)
            traceBackIsland(i - 1, grid[0].length - 1, record[i - 1][j], record[i][j]);

        return;
    }

    private void traceBackIsland(int i, int j, int oldValue, int newValue) {
        if (j < 0)
            return;

        if (record[i][j] == oldValue) {
            record[i][j] = newValue;
        }

        traceBackIsland(i, j - 1, oldValue, newValue);
    }
}