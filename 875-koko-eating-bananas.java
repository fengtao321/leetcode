import java.util.Arrays;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);

        int speedL = 1;
        int speedR = piles[piles.length - 1];
        int ans = speedR;

        while (speedL <= speedR) {
            int mid = (int) Math.floor((speedL + speedR) / 2.0);
            long time = 0; // 1 <= piles[i] <= 10^9 out of memory

            for (int pile : piles) {
                time = time + (int) Math.ceil((double) pile / mid);
            }

            if (time > h) {
                speedL = mid + 1;
            } else {
                speedR = mid - 1;
                ans = Math.min(ans, mid);
            }
        }

        return ans;
    }
}
