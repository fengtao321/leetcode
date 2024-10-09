import java.util.Arrays;

class Solution {
    int[][] sparseTable;

    public int maximumScore(int[] nums, int k) {
        int maxI = nums.length;

        sparseTable = new int[maxI][maxI];

        // build sparse table
        for (int i = 0; i < maxI; i++) {
            sparseTable[i][0] = nums[i];
        }

        for (int j = 1; j < maxI; j++) {
            int range = (int) Math.pow(2, j);
            for (int i = 0; i < maxI - range; i++) {
                sparseTable[i][j] = sparseTable[i][j - 1];
                for (int r = i + 1; r < i + range; r++) {
                    sparseTable[i][j] = Math.min(sparseTable[i][j], sparseTable[r][j]);
                }
            }
        }

        print2D(sparseTable);

        // start to find value from ths sparse table
        int max = 0;
        for (int left = 0; left <= k; left++) {
            for (int right = k; right <= maxI; right++) {
                max = Math.max(max, query(left, right));
            }
        }

        return max;
    }

    private int query(int left, int right) {
        int length = right - left + 1;
        int j = (int) Math.log(length);
        int i1 = left;
        int i2 = right - (1 << j); // faster than int i2 = right - (int) Math.pow(2, j);
        return Math.min(sparseTable[i1][j], sparseTable[i2][j]) * length;
    }

    private void print2D(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
}