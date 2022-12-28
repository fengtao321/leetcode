import java.util.Arrays;

class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int max = 0;

        int l = 0; // left Index
        int length = 0;
        for (int[] tile : tiles) {
            if (tile[1] - tile[0] + 1 >= carpetLen)
                return carpetLen;

            int newLength = length + Math.min((tile[1] - tile[0] + 1), carpetLen + tiles[l][0] - tile[0]);
            max = Math.max(max, newLength);

            while (tile[1] - tiles[l][0] + 1 >= carpetLen) {
                int[] preTile = tiles[l++];
                length = length - (preTile[1] - preTile[0] + 1);
                newLength = length + Math.min((tile[1] - tile[0] + 1), carpetLen + tiles[l][0] - tile[0]);
                max = Math.max(max, newLength);
            }

            length = newLength;
        }

        return max;
    }
}