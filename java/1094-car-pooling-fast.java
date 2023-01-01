class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] record = new int[1000];

        for (int[] trip : trips) {
            record[trip[2]] = record[trip[2]] - trip[0];
            record[trip[1]] = record[trip[1]] + trip[0];
            if (record[trip[1]] > capacity)
                return false;
        }

        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            counter = counter + record[i];
            if (counter > capacity)
                return false;
        }

        return true;
    }
}