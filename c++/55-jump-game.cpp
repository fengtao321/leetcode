class Solution {
public:
    bool canJump(vector<int>& nums) {
        int far = nums[0];
        int length = nums.size();
        if (length == 1) {
            return true;
        }
        for (int i=1; i <= far; i++) {
            int t = i+nums[i];
            far = t > far ? t : far;
            if (far >= length-1) {
                return true;
            }
        }
        return false;
    }
};