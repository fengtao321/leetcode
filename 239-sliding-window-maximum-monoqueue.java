import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1)
            return nums;

        Deque<Integer> monoDeque = new LinkedList<Integer>(); // store index

        int[] ans = new int[nums.length - k + 1];
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // add to monoqueue
            while (!monoDeque.isEmpty() && nums[monoDeque.peekLast()] <= nums[j]) {
                monoDeque.pollLast();
            }
            monoDeque.addLast(j);

            if (j - i + 1 == k) {
                ans[i] = nums[monoDeque.peekFirst()];
                i++;
                if (monoDeque.peekFirst() < i) // i increases, check if the left most index
                    monoDeque.pollFirst();
            }
        }
        return ans;
    }
}