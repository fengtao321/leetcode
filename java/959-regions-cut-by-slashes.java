import java.util.Arrays;

class Solution {

    public int regionsBySlashes(String[] grid) {
        int[][] matrix = convertToMatrix(grid);

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0)
                    continue;
                ans++;
                markMatrix(matrix, i, j, ans);

            }
        }
        return ans;
    }

    private void markMatrix(int[][] matrix, int i, int j, int target) {
        if (i < 0 || j < 0 || i == matrix.length || j == matrix.length || matrix[i][j] != 0)
            return;

        matrix[i][j] = target;
        markMatrix(matrix, i - 1, j, target);
        markMatrix(matrix, i + 1, j, target);
        markMatrix(matrix, i, j - 1, target);
        markMatrix(matrix, i, j + 1, target);
    }

    private int[][] convertToMatrix(String[] grid) {
        int size = grid.length * 3;
        int[][] matrix = new int[size][size];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                switch (grid[i].charAt(j)) {
                    case '/': {
                        matrix[i * 3][j * 3 + 2] = -1;
                        matrix[i * 3 + 1][j * 3 + 1] = -1;
                        matrix[i * 3 + 2][j * 3] = -1;
                        continue;
                    }

                    case '\\': {
                        matrix[i * 3][j * 3] = -1;
                        matrix[i * 3 + 1][j * 3 + 1] = -1;
                        matrix[i * 3 + 2][j * 3 + 2] = -1;
                    }
                }
            }
        }
        // print2D(matrix);
        return matrix;
    }

    private void print2D(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
}