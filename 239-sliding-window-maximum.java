class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        ans[0] = nums[0];

        if (nums.length == 1)
            return ans;

        int left = 0;
        for (int right = 0; right < nums.length - k + 1; right++) {

        }
        return ans;
    }
}