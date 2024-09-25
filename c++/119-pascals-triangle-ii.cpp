class Solution {
public:
    vector<int> getRow(int rowIndex) {
        vector<int> last;
        last.push_back(1);
        if (rowIndex==0) {
            return last;
        }
        last.push_back(1);
        if (rowIndex == 1) {
            return last;
        }
        vector<int> now;
        int line = 2;
        while (line <= rowIndex) {
            now.clear();
            now.push_back(1);
            
            for(std::vector<int>::iterator it = last.begin(); it != last.end()-1; ++it) {
                int temp = *(it) + *(it+1);
                now.push_back(temp);
            }
            now.push_back(1);
            line++;
            last = now;
        }
        return last;
    }
};