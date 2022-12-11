import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> monoDeque = new ArrayDeque<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            // add temperature to the stack
            while (!monoDeque.isEmpty() && temperatures[monoDeque.peekLast()] <= temperatures[i]) {
                monoDeque.pollLast();
            }
            ans[i] = monoDeque.peekLast() == null ? 0 : monoDeque.peekLast() - i;
            monoDeque.add(i);
        }

        return ans;
    }
}