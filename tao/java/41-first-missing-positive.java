
//check question 268, if all positive nums, we can use bit ^
class Solution {
    public int firstMissingPositive(int[] nums) {

        int length = nums.length;
        // first iteration, change all negative to positive MAX
        for (int i = 0; i < length; i++) {
            if (nums[i] <= 0) {
                nums[i] = length + 1; // think about [1,2,3], no use num[i] = length
            }
        }

        // if k=num[i], k is inside [1, length], identity num[k-1] to negative
        for (int i = 0; i < length; i++) {
            int k = nums[i];
            if (k < 0) {
                k = -k;
            }
            if (k > length)
                continue;

            if (nums[k - 1] > 0)
                nums[k - 1] = -nums[k - 1]; // think about [1,1]
        }

        for (int i = 0; i < length; i++) {
            if (nums[i] > 0)
                return i + 1; // return first positive
        }

        return length + 1;
    }
}