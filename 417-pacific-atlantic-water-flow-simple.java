import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    HashSet<String> toPacific = new HashSet<>();
    HashSet<String> toAtlantic = new HashSet<>();
    int[][] heights;
    int maxRow;
    int maxCol;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        maxRow = heights.length;
        maxCol = heights[0].length;

        this.heights = heights;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < maxRow; i++) {
            updateGrid(i, 0, toPacific, 0);
            updateGrid(i, maxCol - 1, toAtlantic, 0);
        }

        for (int i = 0; i < maxCol; i++) {
            updateGrid(0, i, toPacific, 0);
            updateGrid(maxRow - 1, i, toAtlantic, 0);
        }

        // System.out.println(toPacific.toString());
        // System.out.println(toAtlantic.toString());

        // printGrid(toPacific);
        // printGrid(toAtlantic);

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                String key = i + "-" + j;
                if (toPacific.contains(key) && toAtlantic.contains(key)) {
                    Integer[] list = { i, j };
                    ans.add(new ArrayList<>(Arrays.asList(list)));
                }
            }
        }

        return ans;
    }

    private void updateGrid(int i, int j, HashSet<String> set, int preHeight) {
        if (i == maxRow || j == maxCol || i < 0 || j < 0 || set.contains(i + "-" + j) || heights[i][j] < preHeight)
            return;

        set.add(i + "-" + j);
        updateGrid(i - 1, j, set, heights[i][j]);
        updateGrid(i + 1, j, set, heights[i][j]);
        updateGrid(i, j - 1, set, heights[i][j]);
        updateGrid(i, j + 1, set, heights[i][j]);
    }

}