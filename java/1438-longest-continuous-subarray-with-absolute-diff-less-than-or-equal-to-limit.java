class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int ans = 0;
        int maxI = -1;
        int minI = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxI && i <= minI)
                continue;

            if (i > maxI)
                maxI = minI;
            if (i > minI)
                minI = maxI;

            for (int j = i; j < nums.length; j++) {
                if (maxI < 0 || nums[j] >= nums[maxI]) {
                    maxI = j;
                }

                if (minI < 0 || nums[j] <= nums[minI]) {
                    minI = j;
                }

                if (maxI >= 0 && minI >= 0 && (nums[maxI] - nums[minI]) > limit)
                    break;

                ans = Math.max(ans, j + 1 - i);
            }
        }

        return ans;
    }
}