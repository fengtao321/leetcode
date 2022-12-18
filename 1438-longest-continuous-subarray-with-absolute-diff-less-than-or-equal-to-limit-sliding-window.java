import java.util.TreeMap;

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int ans = 0;

        int l = 0;
        int r = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        while (r < nums.length) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }
}