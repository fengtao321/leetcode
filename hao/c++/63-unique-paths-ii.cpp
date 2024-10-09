class Solution {
public:
    
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int length = obstacleGrid.size();
        int width = obstacleGrid[0].size();
        
        int last[width+1];
        int llast=1;
        int j;
        vector<int> vec = obstacleGrid[0];
        for (j = 1; j <= width; j++) {
            if (vec[j-1] == 1) {
                if (j==1) {
                    return 0;
                }
                break;
            }
            last[j] = 1;
        }
        memset(last+(j), 0, (width-j+1)*sizeof(int));
        
        for (int i=1; i < length; i++) {
            vec = obstacleGrid[i];
            llast = 0;
            for (int j = 1; j <= width; j++) {
                if (vec[j-1] == 1) {
                    last[j] = 0;
                } else {
                    last[j] = last[j] + llast;
                }

                llast = last[j];
            }
            
        }
        return last[width];
    }
};