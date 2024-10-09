class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] record = new int[1000];

        for (int[] trip : trips) {
            for (int i = trip[1]; i < trip[2]; i++) {
                record[i] = record[i] + trip[0];
                if (record[i] > capacity)
                    return false;
            }
        }

        return true;
    }
}