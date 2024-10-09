import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] < b[0] || (a[0] == b[0] && a[1] > b[1]))
                return -1;
            return 1;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(0);
        for (int[] interval : intervals) {
            pq.add(interval[1]);
            if (pq.peek() < interval[0]) {
                pq.poll();
            }
        }

        return pq.size();
    }
}