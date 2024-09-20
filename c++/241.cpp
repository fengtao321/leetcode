class Solution {
public:
    vector<int> diffWaysToCompute(string expression) {
        uint8_t size = 0;
        uint8_t pos = 0;
        uint8_t length = expression.length();
        vector<int> base(20);
        uint8_t sym[19] = {0};
        uint8_t value = 0;
        vector<int> result;
        while(pos < length) {
            if ('0' <= expression[pos] && expression[pos] <= '9') {
                value = 10 * value + expression[pos] - '0';
            } else {
                if (expression[pos] == '-') {
                    sym[size] = 0;
                } else if (expression[pos] == '+') {
                    sym[size] = 1;
                } else {
                    sym[size] = 2;
                }
                base.push_back(value);
                value = 0;
                size++;
            }
            pos++;
        }
        base.push_back(value);
        size++;
        if (size == 1) { 
            result.push_back(base[0]); 
            return result;
        }
        if (size == 2) { 
            result.push_back(sym[0] == 0 ? base[0] - base[1] :
                             sym[0] == 1 ? base[0] + base[1]
                                         : base[0] * base[1]);
            return result;
        }
        vector<vector<vector<int>>> dp;
        
        for (pos = 0; pos < size - 1; pos++) {

        }
    }
};