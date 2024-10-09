import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

class Solution {
    public long subArrayRanges(int[] nums) {
        long ans = 0;
        long l1 = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> minstack = new ArrayDeque<>();
        Deque<Integer> maxstack = new ArrayDeque<>();
        minstack.add(-1);
        maxstack.add(-1);

        long[] minSum = new long[nums.length];
        long[] maxSum = new long[nums.length];

        // get minSum
        for (int i = 0; i < nums.length; i++) {
            while (minstack.peekLast() > -1 && nums[minstack.peekLast()] >= nums[i]) {
                int now = minstack.pollLast();
                minSum[now] = l1 * nums[now] * (now - map.get(now)) * (i - now);
            }
            map.put(i, minstack.peekLast());
            minstack.add(i);

            while (maxstack.peekLast() > -1 && nums[maxstack.peekLast()] <= nums[i]) {
                int now = maxstack.pollLast();
                maxSum[now] = l1 * nums[now] * (now - map.get(-now)) * (i - now);
            }
            map.put(-i, maxstack.peekLast());
            maxstack.add(i);
        }

        while (minstack.size() > 1) {
            int now = minstack.pollLast();
            minSum[now] = l1 * nums[now] * (now - map.get(now)) * (nums.length - now);
        }

        while (maxstack.size() > 1) {
            int now = maxstack.pollLast();
            maxSum[now] = l1 * nums[-now] * (now - map.get(now)) * (nums.length - now);
        }

        for (int i = 0; i < nums.length; i++) {
            ans += maxSum[i] - minSum[i];
        }

        return ans;
    }
}
