class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> result;
        vector<int> tmp;
        tmp.push_back(1);
        result.push_back(tmp);
        
        if (numRows == 1) {
            return result;
        }
        tmp.push_back(1);
        result.push_back(tmp);
        for (int i=3; i <= numRows; i++) {
            vector<int> temp;
            temp.push_back(1);
            for (std::vector<int>::iterator it = tmp.begin() ; it != tmp.end()-1; ++it) {
                int res = *it + *(it+1);
                temp.push_back(res);
            }
            temp.push_back(1);
            result.push_back(temp);
            tmp = temp;
        }
        return result;
    }
};