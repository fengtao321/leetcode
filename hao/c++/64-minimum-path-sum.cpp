class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        // DP(a,b) = min(dp(a-1,b), dp(a,b-1)) + grid(a,b)
        int length = grid.size();
        int width = grid[0].size();
        
        int sum[width];
        vector<int> vec = grid[0];
        sum[0] = vec[0];
        for (int j = 1; j < width; j++) {
            sum[j] = sum[j-1]+vec[j];
        }
        int last;
        
        for (int i = 1; i < length; i++) {
            last = -1;
            vector<int> vec = grid[i];
            for (int j = 0; j < width; j++) {
                if (last != -1) {
                    sum[j] = (last > sum[j]? sum[j] : last) + vec[j];
                } else {
                    sum[j] = sum[j] + vec[j];
                }
                last = sum[j];
            }
        }
        return sum[width-1];
    }
};