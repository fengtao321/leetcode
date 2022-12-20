import java.util.TreeMap;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dq = new int[nums.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            Integer lowerKey = map.lowerKey(nums[i]);

            dq[i] = lowerKey == null ? 1 : dq[map.get(lowerKey)] + 1;
            max = Math.max(max, dq[i]);

            Integer ceilingKey = map.ceilingKey(nums[i]);
            while (ceilingKey != null && dq[map.get(ceilingKey)] <= dq[i]) {
                map.remove(ceilingKey);
                ceilingKey = map.ceilingKey(nums[i]);
            }
            map.put(nums[i], i);
        }

        return max;
    }
}