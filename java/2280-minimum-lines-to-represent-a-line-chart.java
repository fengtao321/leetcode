import java.util.Arrays;

class Solution {
    public int minimumLines(int[][] stockPrices) {
        if (stockPrices.length == 2)
            return 1;

        Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
        int ans = 1;
        int pi1 = 0;
        int pi2 = 1;

        int i = 2;
        while (i < stockPrices.length) {
            if (!isSameLine(stockPrices[pi1], stockPrices[pi2], stockPrices[i])) {
                pi1 = i - 1;
                pi2 = i;
                ans++;
            }
            i++;
        }

        return ans;
    }

    private boolean isSameLine(int[] p1, int[] p2, int[] p3) {
        return (p1[1] - p2[1]) * (p1[0] - p3[0]) == (p1[1] - p3[1]) * (p1[0] - p2[0]);
    }
}