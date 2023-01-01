class Solution {
    long[] dp;
    int[][] points;

    public long maxPoints(int[][] points) {
        dp = new long[points[0].length];
        this.points = points;

        // when finding min/max, store previous num in HashMap, pq, if have a limit, or
        // an array without limit, and scan from left + right
        // think what to put in the array!
        for (int i = points.length - 1; i >= 0; i--) {
            long[] minFromLeft = findMaxLeft(i);
            long[] minFromRight = findMaxRight(i);

            // fill row
            for (int j = 0; j < points[0].length; j++) {
                dp[j] = Math.max(minFromLeft[j], minFromRight[j]) + points[i][j];
            }
        }
        return maxNumInRow();
    }

    private long[] findMaxRight(int rowI) {
        int size = points[0].length;
        long[] minFromRight = new long[size];
        minFromRight[size - 1] = dp[size - 1];
        for (int j = size - 2; j >= 0; j--) {
            minFromRight[j] = Math.max(minFromRight[j + 1] - 1, dp[j]);
        }
        return minFromRight;
    }

    private long[] findMaxLeft(int rowI) {
        int size = points[0].length;
        long[] minFromLeft = new long[size];
        minFromLeft[0] = dp[0];

        for (int j = 1; j < size; j++) {
            minFromLeft[j] = Math.max(minFromLeft[j - 1] - 1, dp[j]);
        }
        return minFromLeft;
    }

    private long maxNumInRow() {
        long max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}