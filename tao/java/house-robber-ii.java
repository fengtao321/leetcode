class Solution {
    public int rob(int[] nums) {
        int n = nums.length - 1;
        return Math.max(dp(0, n - 1, nums), dp(1, n, nums));
    }

    private int dp(int start, int end, int[] nums) {
        if (end < start)
            return nums[0];
        if (end == start)
            return nums[start];
        int[] dp = new int[nums.length];
        int i = start;
        dp[i++] = nums[start];
        dp[i++] = Math.max(nums[start], nums[start + 1]);

        for (; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[end];
    }
}