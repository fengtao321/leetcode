import java.util.HashMap;

class Solution {
    public int findMaxLength(int[] nums) {
        int max = 0;
        HashMap<Integer, Integer> table = new HashMap<>();
        table.put(0, -1); // before start, 1, 0 balance is 0

        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            counter = nums[i] == 1 ? counter + 1 : counter - 1;

            if (table.containsKey(counter)) {
                max = Math.max(max, i - table.get(counter));
            } else {
                table.put(counter, i);
            }
        }

        return max;
    }
}