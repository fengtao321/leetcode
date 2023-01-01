import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubarray(int[] nums, int p) {
        // first, we need find the target remainder to get rid of
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            target = (target + nums[i]) % p;
        }

        if (target == 0)
            return 0;

        int sum = 0;
        int ans = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % p;
            int sumPair = (p + sum - target) % p; // for example, if p is 6, target is 4, remainder is 4, we want to
                                                  // find 0, if remainder is 2, we want to find 4, as 6-4 =2. and now
                                                  // the remainder is 2, 2+2 = 4 -> target
            // sumpPair = if( sum >= target) -> sum - target
            // if(sum < target) sum + p -target

            if (map.containsKey(sumPair)) {
                ans = Math.min(ans, i - map.get(sumPair));
            }
            map.put(sum, i);
        }

        return ans == nums.length ? -1 : ans;
    }
}