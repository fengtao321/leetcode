class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        vector<int>::iterator it1 = nums.begin();
        vector<int>::iterator it2 = nums.begin()+1;
        vector<int>::iterator it3 = nums.end()-1;
        sort(nums.begin(), nums.end());
        int t =0;
        int sum = 0;
        int miniGap = -1;
        int absMiniGap = 0;

        while (it1 <= nums.end()-3) {
            // cout << *it1 << endl;
            it2 = it1+1;
            it3 = nums.end()-1;
            t = *it1;
            while (it2 < it3) {
                // Could Update for this part
                sum = (t+*it2+*it3 -target);
                // printf("%d%d%d\n",t,*it2,*it3);
                if (abs(sum) < miniGap || miniGap == -1) {
                    
                    miniGap = abs(sum);
                    absMiniGap = t+*it2+*it3;
                }
                
                // Skip to another different value
                
                if (sum < 0) {
                    while (*it2 == *(++it2) && it2 < it3) {}
                } else {
                    while (*it3 == *(--it3) && it2 < it3) {}
                }
                
            }
            while (*it1 == *(++it1) && it1 <= nums.end()-3) {}
        }
        

        return absMiniGap;
        
    }
};