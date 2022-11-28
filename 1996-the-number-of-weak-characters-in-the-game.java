import java.util.Arrays;

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        // Be careful about the sorting sequence
        Arrays.sort(properties, (int[] a, int[] b) -> {
            if ((a[0] - b[0] > 0) || (a[0] == b[0] && a[1] - b[1] < 0))
                return -1;
            return 1;
        });

        // System.out.println(Arrays.deepToString(properties));

        int weakNum = 0;
        int max = 0;

        for (int i = 0; i < properties.length; i++) {
            if (properties[i][1] < max) {
                weakNum++;
            } else {
                max = properties[i][1];
            }

        }

        return weakNum;
    }
}