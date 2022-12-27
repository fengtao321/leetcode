import java.util.HashMap;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int size = nums.length;
        int[] prefix = new int[size];
        prefix[0] = nums[0];
        for (int i = 1; i < size; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        HashMap<Integer, Integer> curr = new HashMap<>();
        curr.put(nums[size - 1], 1);
        curr.put(-nums[size - 1], curr.getOrDefault(-nums[size - 1], 0) + 1);

        for (int i = size - 2; i >= 0; i--) {
            HashMap<Integer, Integer> next = new HashMap<>();
            for (int pre : curr.keySet()) {
                int sum = pre + nums[i];
                if (sum + prefix[i] >= target && sum - prefix[i] <= target) {
                    next.put(sum, next.getOrDefault(sum, 0) + curr.get(pre));
                }

                sum = pre - nums[i];
                if (sum + prefix[i] >= target && sum - prefix[i] <= target) {
                    next.put(sum, next.getOrDefault(sum, 0) + curr.get(pre));
                }
            }
            curr = next;
        }

        return curr.getOrDefault(target, 0);
    }
}