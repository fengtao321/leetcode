// https://www.youtube.com/watch?v=ONuuGA8U_iM

class Solution {
    int[][] sparseTable;

    public int maximumScore(int[] nums, int k) {
        int left = k;
        int right = k;
        int min = nums[k];
        int ans = min;

        int rightMax = nums.length - 1;

        while (left > 0 || right < rightMax) {
            if (left > 0 && right < rightMax) { // we can move either left or right
                if (nums[left - 1] > nums[right + 1]) { // <--- move to a bigger num, for example [3,7,4], we can either
                                                        // get [3,7] or [7,4], same length, but always choose the size
                                                        // with larger number to move
                    left--;
                    min = Math.min(min, nums[left]);
                } else { // right +1 is larger or equal to left - 1
                    right++;
                    min = Math.min(min, nums[right]);
                }
            } else if (left > 0) { // only left can be moved, right==rightMax
                left--;
                min = Math.min(min, nums[left]);
            } else { // only right can be moved, left==0
                right++;
                min = Math.min(min, nums[right]);
            }
            ans = Math.max(ans, min * (right - left + 1));
        }

        return ans; // left = 0, right = rightMax
    }
}