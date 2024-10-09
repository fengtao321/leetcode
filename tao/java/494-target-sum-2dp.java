import java.util.HashMap;

class Solution {
    HashMap<String, Integer> dp = new HashMap<>();
    int[] nums;

    public int findTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        return dfs(target, 0);
    }

    private int dfs(int value, int level) {
        if (level == nums.length - 1) {
            if (nums[nums.length - 1] == 0 && value == 0)
                return 2;
            return (value == nums[nums.length - 1] || value == -nums[nums.length - 1]) ? 1 : 0;
        }

        String key = value + "-" + level;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int sum = dfs(value + nums[level], level + 1) + dfs(value - nums[level], level + 1);
        dp.put(key, sum);
        return sum;
    }
}