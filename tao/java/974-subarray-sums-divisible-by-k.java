import java.util.Hashtable;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int length = nums.length;

        int total = 0;
        int lastRemainder = 0;
        Hashtable<Integer, Integer> table = new Hashtable<>();
        table.put(0, 1);

        for (int i = 0; i < length; i++) {
            int remainder = getRemainder(nums[i] + lastRemainder, k);

            if (table.containsKey(remainder)) {
                int counter = table.get(remainder);
                total += counter;
                table.put(remainder, counter + 1);

            } else {
                table.put(remainder, 1);
            }

        }

        return total;
    }

    private int getRemainder(int num, int k) {
        int remainder = num % k;
        return remainder >= 0 ? remainder : k + remainder;
    }
}