class Solution {
public:
    int maxArea(vector<int>& height) {

        int volume = 0;

        int left = 0;
        int right = height.size()-1;
        int tmp = 0;
        int gap = right;

        while (gap > 0) {
            
            if (height[left] < height[right]) {
                tmp = gap*height[left];
                left++;
            } else {
                tmp = gap*height[right];
                right--;
            }
            volume = tmp > volume ? tmp : volume;
            gap--;
        }
        return volume;
        
    }
};