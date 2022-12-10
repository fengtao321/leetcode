import java.util.PriorityQueue;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1)
            return nums;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        int[] ans = new int[nums.length - k + 1];
        int i = 0;

        for (int j = 0; j < nums.length; j++) {
            queue.add(new int[] { nums[j], j });
            if (i + k - 1 == j) {
                int[] top = queue.peek();
                while (top[1] < i) {
                    queue.poll();
                    top = queue.peek();
                }
                ans[i] = top[0];
                i++;
            }
        }
        return ans;
    }
}