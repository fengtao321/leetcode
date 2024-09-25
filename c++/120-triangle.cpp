class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int m = triangle.size();
        vector<int> saveResult;
        saveResult.push_back(triangle[0][0]);
        vector<int> temp;
        for (int i = 1; i < m; i++) {
            temp.clear();
            vector<int> tmp = triangle[i];
            temp.push_back(saveResult[0]+tmp[0]);
            for (int j = 1; j < i; j++) {
                if (saveResult[j-1] < saveResult[j]) {
                    temp.push_back(saveResult[j-1]+tmp[j]);
                } else {
                    temp.push_back(saveResult[j]+tmp[j]);
                }
            }
            temp.push_back(saveResult[i-1]+tmp[i]);
            
            saveResult = temp;
            // for (int p = 0; p < saveResult.size(); p++) {
            //     cout << saveResult[p];
            // }
            // cout << "\n";
        }
        
        int min = saveResult[0];
        for (int i = 1; i < m; i++) {
            if (saveResult[i] < min) {
                min = saveResult[i];
            }
        }
        return min;
    }
};