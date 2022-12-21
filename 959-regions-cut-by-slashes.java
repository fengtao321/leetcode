import java.util.Arrays;

class Solution {

    public int regionsBySlashes(String[] grid) {
        int[][] matrix = convertToMatrix(grid);

        int ans = 1;
        int counter = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0)
                    continue;
                int left = 0;
                int top = 0;
                if (j > 0 && matrix[i][j - 1] > 0) {
                    left = matrix[i][j - 1];
                    // matrix[i][j] = matrix[i][j - 1];
                }

                if (i > 0 && matrix[i - 1][j] > 0) {
                    top = matrix[i - 1][j];
                    // matrix[i][j] = matrix[i - 1][j];
                    // continue;
                }
                if (left == top) {
                    matrix[i][j] = left;
                    if (matrix[i][j] == 0) {
                        matrix[i][j] = counter++;
                        ans++;
                    }

                    continue;
                }

                if (left == 0) {
                    matrix[i][j] = top;
                    continue;
                }

                if (top == 0) {
                    matrix[i][j] = left;
                    continue;
                }

                ans--;
                // print2D(matrix);

                if (left < top) {
                    matrix[i][j] = left;
                    adjustMatrix(matrix, i - 1, j, left);
                } else {
                    matrix[i][j] = top;
                    adjustMatrix(matrix, i, j - 1, top);
                }

            }
        }
        print2D(matrix);
        return ans;
    }

    private void adjustMatrix(int[][] matrix, int i, int j, int target) {
        if (i < 0 || j < 0 || j == matrix.length || matrix[i][j] <= target)
            return;

        matrix[i][j] = target;
        adjustMatrix(matrix, i - 1, j, target);
        adjustMatrix(matrix, i, j - 1, target);
        adjustMatrix(matrix, i, j + 1, target);
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