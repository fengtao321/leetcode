class Solution {
public:
    int trying(int a, int b, int& ans, vector<vector<int>>& grid, int countleft) {
        if (a<0||b<0||a>=grid.size()||b>=grid[0].size()) {
            return 0;
        }
        int bol = grid[a][b];
        if (bol == 2) {
            if (countleft == 0) {
                ans++;
                return 1;
            } else {
                return 0;
            }
        }
        if (bol == 1 || bol == -1) {
            return 0;
        } 
        grid[a][b] = -1;
        trying(a-1, b, ans, grid, countleft-1);
        trying(a+1, b, ans, grid, countleft-1);
        trying(a, b-1, ans, grid, countleft-1);
        trying(a, b+1, ans, grid, countleft-1);
        grid[a][b] = bol;
        return 0;
    }
    
    int uniquePathsIII(vector<vector<int>>& grid) {
        int length = grid.size();
        int width = grid[0].size();
        int a,b;
        int counts = 0;
        for (int i = 0; i < length; i++) {
            vector<int> vec = grid[i];
            for (int j = 0; j < width; j++) {
                if (vec[j] == 1) {
                    a=i;
                    b=j;
                } else if (vec[j] == 0) {
                    counts++;
                }
            }
        }
        int ans = 0;
        trying(a-1,b,ans,grid,counts);
        trying(a+1,b,ans,grid,counts);
        trying(a,b-1,ans,grid,counts);
        trying(a,b+1,ans,grid,counts);
        return ans;
    }
};