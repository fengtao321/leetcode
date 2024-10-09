import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

// https://leetcode.cn/problems/sum-of-subarray-minimums/solution/dan-diao-zhan-by-heren1229-j8nz/
class Solution {
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        long l1 = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(-1);

        HashMap<Integer, Integer> tree = new HashMap<>(); // index of node,

        for (int i = 0; i < arr.length; i++) {
            while (stack.peekLast() > -1 && arr[stack.peekLast()] >= arr[i]) {
                int index = stack.pollLast();
                int pre = tree.get(index);
                sum = (sum + l1 * arr[index] * (index - pre) * (i - index)) % 1000000007;
            }

            tree.put(i, stack.peekLast());
            stack.add(i);
        }

        while (stack.size() > 1) {
            int index = stack.pollLast();
            int pre = tree.get(index);
            sum = (sum + l1 * arr[index] * (index - pre) * (arr.length - index)) % 1000000007;
        }
        return (int) sum;
    }
}