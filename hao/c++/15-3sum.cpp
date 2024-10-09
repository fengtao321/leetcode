class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<int>::iterator it1 = nums.begin();
        vector<int>::iterator it2 = nums.begin()+1;
        vector<int>::iterator it3 = nums.end()-1;
        sort(nums.begin(), nums.end());
        vector<int> newVec = {};
        int t =0;

        vector<vector<int>> res = {};

        while (it1 <= nums.end()-3) {
            // cout << *it1 << endl;
            it2 = it1+1;
            it3 = nums.end()-1;
            t = -(*it1);
            while (it2 < it3) {
                if (*it2+*it3 == t) {
                    newVec = {*it1, *it2, *it3};
                    res.push_back(newVec);
                    // Skip to another different value
                    while (*it2 == *(++it2) && it2 < it3) {

                    }
                    while (*it3 == *(--it3) && it2 < it3) {

                    }
                } else if (*it2 + *it3 < t) {
                    it2++;
                } else {
                    it3--;
                }
                
            }
            while (*it1 == *(++it1) && it1 <= nums.end()-3) {}
        }
        

        return res;
    }
};