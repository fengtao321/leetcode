import java.util.Arrays;
import java.util.HashSet;

class Solution {

    int[] nums;

    public boolean canPartition(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) > 0)
            return false;

        int target = sum / 2;
        HashSet<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > target)
                return false;

            HashSet<Integer> nextDp = new HashSet<>();
            for (int value : dp) {
                nextDp.add(value);

                int subSum = value + nums[i];
                if (subSum == target)
                    return true;
                if (subSum < target)
                    nextDp.add(subSum);
            }
            dp = nextDp;
        }
        return false;
    }

}