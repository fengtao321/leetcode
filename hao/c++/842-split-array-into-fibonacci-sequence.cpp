class Solution {
public:
    int mov(int temp, int single) {
        if (temp > 214748364) {
            return -1;
        }
        if (temp == 214748364 && single >7) {
            return -1;
        }
        return temp*10+single;
    }
    
    vector<int> checkFib(int desire, int last, string& num, int index) {
        vector<int> results;
        vector<int> vec1;
        
        if(index >= num.size()) {
            return vec1;
        }
        
        if (desire==0 && last == 0) {
            for (int i = index; i < num.size(); i++) {
                if (num[i] == '0') {
                    results.push_back(0);
                } else {
                    return vec1;
                }
            }
            return results;
        }
        if (num[index] == '0' && desire != 0) {
            return vec1;
        }
        int offset = 0;
        int temp = desire;
        while(temp>=10) {
            temp = temp / 10;
            offset++;
        }
        if(index+offset>num.size()-1) {
            return vec1;
        }
        temp = desire;
        for (int i = index+offset; i >= index; i--) {
            if (num[i]-'0' != temp%10){
                return vec1;
            }
            temp = temp /10;
        }
        results.push_back(desire);
        if (index+offset != num.size()-1) {
            if (desire > 2147483647-last) {
                return vec1;
            }
            vector<int> vec = checkFib(desire+last, desire, num, index+offset+1);
            if (vec.size() == 0) {
                return vec1;
            }
            results.insert(results.end(), vec.begin(), vec.end());
        }
        return results;
    }
    
    vector<int> splitIntoFibonacci(string num) {
        int length = num.length();
        vector<int> results;
        vector<int> vec;
        
        if (num[0]=='0') {
            int temp = 0;
            for (int i = 1; i <= (length-1)/2; i++) {
                temp = mov(temp, num[i]-'0');
                if (temp < 0) {
                    return vec;
                }
                vec = checkFib(temp, temp, num, i+1);
                if (vec.size() > 0) {
                    results.push_back(0);
                    results.push_back(temp);
                    results.insert(results.end(), vec.begin(), vec.end());
                    return results;
                }
            }
            return results;
        }
        int last = 0;
        
        for (int j = 1; j <= (length-1)/2; j++) {
            last = mov(last, num[j-1]-'0');
            if (last < 0) {
                return vec;
            }
            int temp = 0;
            int maxLength = length-2*j > (length-1)/2 ? (length-1)/2 : length-2*j;
            for (int i=1; i <= maxLength; i++) {
                
                temp = mov(temp, num[j+i-1]-'0');
                if (temp < 0) {
                    break;
                }
                if (temp > 2147483647-last) {
                    break;
                }
                vec = checkFib(temp+last, temp, num, j+i);
                if (vec.size() > 0) {
                    results.push_back(last);
                    results.push_back(temp);
                    results.insert(results.end(), vec.begin(), vec.end());
                    return results;
                }
            }
        }
        return vec;
    }
};