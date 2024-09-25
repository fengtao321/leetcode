class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        
        int length = nums.size();
        int last = nums[0];
        int sum =last;
        for (int i = 1; i < length; i++) {
            if (last < 0) {
                last = nums[i];
            } else {
                last += nums[i];
            }
            if (last > sum) {
                sum = last;
            }
        }
        return sum;
    }
};