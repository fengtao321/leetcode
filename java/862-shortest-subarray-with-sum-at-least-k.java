import java.util.PriorityQueue;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (int) (a[0] - b[0])); // sum, indexL
        pq.add(new long[] { 0, -1 });

        long sum = 0;
        for (int r = 0; r <= nums.length; r++) {
            while (sum - pq.peek()[0] >= k) {
                long[] left = pq.poll();
                ans = Math.min(ans, (int) (r - left[1] - 1));
            }
            if (r < nums.length) {
                sum = sum + nums[r];
                pq.add(new long[] { sum, r });
            }

        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}