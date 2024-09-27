class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_map<int,bool> exist;
        int n = nums.size();
        for(int i = 0; i < n; i++) {
            if (exist[nums[i]]) {
                return true;
            } else {
                exist[nums[i]] = true;
            }
        }
        return false;
    }
};