class Solution {
public:
    int trap(vector<int>& height) {
        int size = height.size();
        if (size <= 1) {
            return 0;
        }
        int leftHeight[size];
        int lastRightHeight = 0;
        
        leftHeight[0] = 0;
        leftHeight[1] = height[0];
        
        for (int i = 2; i < size; i++) {
            leftHeight[i] = leftHeight[i-1] > height[i-1] ? leftHeight[i-1] : height[i-1];
            
        }
        int sum = 0;
        int thisRightHeight;
        for (int i = size-2; i >= 0; i--) {
            thisRightHeight = lastRightHeight > height[i+1] ? lastRightHeight : height[i+1];
            int a = (leftHeight[i] < thisRightHeight ? leftHeight[i] : thisRightHeight);
            if (a>height[i]) {
                sum+=a-height[i];
            }
            lastRightHeight = thisRightHeight;
        }
        return sum;
    }
};