import java.util.PriorityQueue;

class Solution {
    public int maximumProduct(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0] + k;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }

        while (k > 0) {
            int a = pq.poll();
            int b = pq.peek();
            if (b == a) {
                pq.add(a + 1);
                k--;
            }

            else if ((b - a) < k) {
                pq.add(b);
                k = k - b + a;
            } else {
                pq.add(a + k);
                break;
            }

        }

        long ans = 1;
        int mod = (int) 1e9 + 7;
        while (!pq.isEmpty()) {
            ans = (ans * pq.poll()) % mod;
        }

        return (int) ans;
    }
}