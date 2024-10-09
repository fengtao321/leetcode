class Solution {
public:
    int jump(vector<int>& nums) {
        int length = nums.size();
        if (length <= 2) {
            return length-1;
        }
        int minSteps = 1;
        int maxIndex;
        int minIndex;
        int start;
        if (nums[0] >= length-1) {
            return 1;
        }
        minIndex = nums[0];
        start = 1;
        maxIndex = minIndex;
        
        while (true) {
            for (int i = start; i <= minIndex; i++) {
                if (i+nums[i] > maxIndex) {
                    maxIndex = i+nums[i];
                    if (maxIndex >= length-1) {
                        return minSteps+1;
                    }
                }
            }
            minSteps++;
            start = minIndex;
            minIndex = maxIndex;
            
        }        
    }
};