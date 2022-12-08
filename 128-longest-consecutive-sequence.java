import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int longestConsecutive(int[] nums) {
        // Set<Integer> set = new HashSet<>();
        // for (int i = 0; i < nums.length; i++) {
        // set.add(nums[i]);
        // }

        // change nums to set
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        int max = 0;
        for (int num : nums) {
            // only handle the header of the list, and add all the followings.
            if (set.contains(num - 1))
                continue;

            int counter = 1;
            while (set.contains(++num)) {
                counter++;
            }
            max = Math.max(max, counter);
        }

        return max;
    }
}