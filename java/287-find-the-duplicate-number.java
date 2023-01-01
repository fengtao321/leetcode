// we can not use bit ^, since there is only one repeated number, it might be [2,2,2,2]

// class Solution {
//     public int findDuplicate(int[] nums) {
//         int ans = 0;
//         for(int i=0; i<nums.length; i++) {
//             ans = ans ^ i ^ nums[i];
//         }

//         return ans;
//     }
// }

class Solution {
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            int key = num - 1;
            if (nums[key] < 0) {
                return num;
            }

            nums[key] = -nums[key];
        }

        return nums.length;
    }
}