import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        LinkedList<Integer> flowerList = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long fullNum = 0;
        for (int flower : flowers) {
            if (flower >= target) {
                fullNum++;
            } else {
                flowerList.add(flower);
                pq.add(flower);
            }
        }
        Collections.sort(flowerList);

        int min = flowerList.get(0);
        long ans = fullNum * full + min * partial;

        for (int i = flowerList.size() - 1; i >= 0 && newFlowers > 0; i++) {
            long left = newFlowers - (target - flowerList.get(i));
            if (left >= 0) {
                fullNum++;
                newFlowers = left;
                pq.remove(flowerList);
            }

            int currMin = getMin(left);
            ans = Math.max(ans, fullNum * full + currMin * partial);
        }
        return ans;

    }
}