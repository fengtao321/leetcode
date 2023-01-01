import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int sum = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            int size = queue.size();
            sum += arr[i];
            queue.add(arr[i]);
            for (int j = 0; j < size; j++) {
                int min = Math.min(queue.poll(), arr[i]);
                sum = (sum + min) % 1000000007;
                queue.add(min);
            }
        }
        return sum;
    }
}