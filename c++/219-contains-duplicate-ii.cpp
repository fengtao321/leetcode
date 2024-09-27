class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        unordered_map<int,int> pls;
        int i = 1;
        for(std::vector<int>::iterator it = nums.begin(); it != nums.end(); it++, i++) {
            if (pls[*it] == 0) {
                pls[*it] = i;
            } else {
                if (pls[*it] >= i-k) {
                    return true;
                }
                pls[*it] = i;
            }
        }
        return false;
        
    }
};