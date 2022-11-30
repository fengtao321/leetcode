import java.util.Arrays;

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[][] record = new int[m][n];

        for (int[] row : record) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j, 0, matrix, record));
            }
        }
        return max;
    }

    private int dfs(int i, int j, int lastNum, int[][] matrix, int[][] record) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] <= lastNum) {
            return 0;
        }

        if (record[i][j] > -1) {
            return record[i][j];
        }

        int up = dfs(i - 1, j, matrix[i][j], matrix, record);
        int down = dfs(i + 1, j, matrix[i][j], matrix, record);
        int left = dfs(i, j - 1, matrix[i][j], matrix, record);
        int right = dfs(i, j + 1, matrix[i][j], matrix, record);

        int pathNum = 1 + max(up, down, left, right);
        record[i][j] = pathNum;
        return pathNum;
    }

    private int max(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }
}