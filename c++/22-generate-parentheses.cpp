class Solution {
public:
    vector<string> generatePrevStrings(int n, int left, int right) {
        vector<string> vec;
        if (right<0) {
            return vec;
        }
        if (right>left){
            return vec;
        }
        if (n==1) {
            vec.push_back("(");
        }
        
        else {
            vector<string> vec1 = generatePrevStrings(n-1, left, right-1);
            
            for (int i = 0; i < vec1.size(); i++) {
                vec1[i] += ')';
            }
            vector<string> vec2 = generatePrevStrings(n-1, left-1, right);
            for (int i = 0; i < vec2.size(); i++) {
                vec2[i] += '(';
            }
            vec.insert(vec.end(), vec1.begin(), vec1.end());
            vec.insert(vec.end(), vec2.begin(), vec2.end());
        }
        //printf("LEFT%d %d %d\n", n, left, vec.size());
        return vec;
    }
    
    vector<string> generateLastStrings(int n, int left, int right) {
        vector<string> vec;
        if (left<0) {
            return vec;
        }
        if (right<left){
            return vec;
        }
        if (n==1) {
            vec.push_back(")");
        }
        
        else {
            vector<string> vec1 = generateLastStrings(n-1, left, right-1);
            for (int i = 0; i < vec1.size(); i++) {
                vec1[i] = ")" + vec1[i];
            }
            vector<string> vec2 = generateLastStrings(n-1, left-1, right);
            for (int i = 0; i < vec2.size(); i++) {
                vec2[i] = "(" + vec2[i];
            }
            vec.insert(vec.end(), vec1.begin(), vec1.end());
            vec.insert(vec.end(), vec2.begin(), vec2.end());
        }
        //printf("RIGHT%d %d %d\n", n, left, vec.size());
        return vec;
    }
    
    vector<string> generateParenthesis(int n) {
        vector<string> vec;
        for (int i = 0; i <=n/2; i++) {
            vector<string> vec1 = generatePrevStrings(n, n-i,i);
            vector<string> vec2 = generateLastStrings(n, i, n-i);
            for (int j = 0; j < vec1.size(); j++) {
                for (int k = 0; k < vec2.size(); k++) {
                    vec.push_back(vec1[j]+vec2[k]);
                }
            }
        }
        return vec;
    }
};