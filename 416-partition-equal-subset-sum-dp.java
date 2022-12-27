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

        HashSet<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int i = nums.length - 1; i >= 0; i--) {
            HashSet<Integer> nextDp = new HashSet<>();
            for (int value : dp) {
                nextDp.add(value);
                nextDp.add(value + nums[i]);
            }
            dp = nextDp;
        }
        return dp.contains(sum / 2);
    }

}