import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    boolean[][] toPacific;
    boolean[][] toAtlantic;
    int[][] heights;
    int maxRow;
    int maxCol;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        maxRow = heights.length;
        maxCol = heights[0].length;
        toPacific = new boolean[maxRow][maxCol];
        toAtlantic = new boolean[maxRow][maxCol];

        this.heights = heights;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < maxRow; i++) {
            toPacific[i][0] = true;
            updateGrid(i, 1, toPacific);
            toAtlantic[i][maxCol - 1] = true;
            updateGrid(i, maxCol - 2, toAtlantic);
        }

        for (int i = 0; i < maxCol; i++) {
            toPacific[0][i] = true;
            updateGrid(1, i, toPacific);
            toAtlantic[maxRow - 1][i] = true;
            updateGrid(maxRow - 2, i, toAtlantic);
        }

        printGrid(toPacific);
        printGrid(toAtlantic);

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (toPacific[i][j] && toAtlantic[i][j]) {
                    Integer[] list = { i, j };
                    ans.add(new ArrayList<>(Arrays.asList(list)));
                }
            }
        }

        return ans;
    }

    private void updateGrid(int i, int j, boolean[][] grid) {
        if (i >= maxRow || j >= maxCol || i < 0 || j < 0 || grid[i][j])
            return;

        boolean topCondition = i > 0 && grid[i - 1][j] && heights[i][j] >= heights[i - 1][j];
        boolean bottomCondition = i + 1 < maxRow && grid[i + 1][j] && heights[i][j] >= heights[i + 1][j];
        boolean leftCondition = j > 0 && grid[i][j - 1] && heights[i][j] >= heights[i][j - 1];
        boolean rightCondition = j + 1 < maxCol && grid[i][j + 1] && heights[i][j] >= heights[i][j + 1];

        if (topCondition || bottomCondition || leftCondition || rightCondition) {
            grid[i][j] = true;
            updateGrid(i + 1, j, grid);
            updateGrid(i - 1, j, grid);
            updateGrid(i, j + 1, grid);
            updateGrid(i, j - 1, grid);
        }
    }

    private void printGrid(boolean[][] grid) {
        System.out.println("========================");
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                System.out.print(grid[i][j] + "->");
            }
            System.out.println("");
        }
    }
}