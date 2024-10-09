import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = queries[i];
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(queries);

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

        int i = 0;
        int j = 0;
        while (i < queries.length) {
            int q = queries[i];
            while (pq.size() > 0 && pq.peek()[1] < q) {
                pq.poll();
            }

            while (j < intervals.length && intervals[j][0] <= q) {
                if (intervals[j][1] >= q) {
                    int length = intervals[j][1] - intervals[j][0] + 1;
                    pq.add(new int[] { length, intervals[j][1] });
                }
                j++;
            }

            int value = pq.size() > 0 ? pq.peek()[0] : -1;
            map.put(q, value);
            i++;
        }

        for (int k = 0; k < ans.length; k++) {
            ans[k] = map.get(ans[k]);
        }

        return ans;
    }
}