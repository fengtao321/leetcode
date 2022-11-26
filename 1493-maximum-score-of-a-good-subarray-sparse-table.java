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

        for (int j = 1; (1 << j) < maxI; j++) {
            for (int i = 0; (i + (1 << j) - 1) < maxI; i++) {
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }

        // print2D(sparseTable);

        // start to find value from ths sparse table
        int max = nums[k];
        int left = k;
        int right = k;

        while (left > 0 || right < nums.length - 1) {
            if (left > 0 && right < (nums.length - 1)) {
                if (nums[left - 1] > nums[right + 1]) {
                    left--;
                } else {
                    right++;
                }
            } else if (left > 0) {
                left--;
            } else {
                right++;
            }

            max = Math.max(max, query(left, right));
        }

        return max;
    }

    private int query(int left, int right) {
        int length = right - left + 1;
        int j = (int) (Math.log(length) / Math.log(2));
        // int j = Integer.numberOfTrailingZeros(Integer.highestOneBit(length));
        int i1 = left;
        int i2 = right - (1 << j) + 1; // faster than int i2 = right - (int) Math.pow(2, j);
        int ans = Math.min(sparseTable[i1][j], sparseTable[i2][j]) * length;
        return ans;
    }

    private void print2D(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
}