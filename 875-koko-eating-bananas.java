import java.util.Arrays;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);

        int speedL = 1;
        int speedR = piles[piles.length - 1];

        while (speedL < speedR) { // no equal to avoid loop
            int mid = (speedL + speedR) / 2;
            double time = 0;

            for (int pile : piles) {
                time = time + Math.ceil((double) pile / mid);
            }

            if (time > h) {
                speedL = mid + 1;
            } else {
                speedR = mid; // right limit will keep the ans
            }
        }

        return speedR;
    }
}