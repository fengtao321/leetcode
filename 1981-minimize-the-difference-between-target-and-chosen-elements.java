import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        for (int i = 0; i < mat.length; i++) {
            int size = queue.size();
            HashSet<Integer> set = new HashSet<>();

            for (int j = 0; j < size; j++) {
                int pre = queue.poll();
                Arrays.sort(mat[i]);
                for (int k = 0; k < mat[0].length; k++) {
                    int curr = mat[i][k] + pre;
                    if (set.contains(curr))
                        continue;

                    queue.add(curr);
                    set.add(curr);

                    if (curr >= target) {
                        break;
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int curr = Math.abs(queue.poll() - target);
            if (curr == 0)
                return 0;

            if (ans > curr) {
                ans = curr;
            }
        }

        return ans;
    }
}