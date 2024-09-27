class Solution {
public:
    
    long mov(long temp, int single) {
        return temp*10+single;
    }
    
    bool checkFib(long desire, int last, string& num, int index) {
        // Boundaries
        if(index >= num.size()) {
            return false;
        }
        
        if (num[index] == '0' && desire != 0) {
            return false;
        }
        // All zeros
        if (desire==0 && last == 0) {
            for (int i = index; i < num.size(); i++) {
                if (num[i] != '0') {
                    return false;
                }
            }
            return true;
        }
        // Get How many bits
        int offset = 0;
        long temp = desire;
        while(temp>=10) {
            temp = temp / 10;
            offset++;
        }
        if(index+offset>num.size()-1) {
            return false;
        }
        temp = desire;
        // compare
        for (int i = index+offset; i >= index; i--) {
            if (num[i]-'0' != temp%10){
                return false;
            }
            temp = temp /10;
        }
        // Go further
        if (index+offset != num.size()-1) {
            return checkFib(desire+last, desire, num, index+offset+1);
        } else {
            return true;
        }
    }
    
    bool isAdditiveNumber(string num) {
        int length = num.length();
        // Start with 0
        if (num[0]=='0') {
            long temp = 0;
            for (int i = 1; i <= (length-1)/2; i++) {
                temp = mov(temp, num[i]-'0');
                if (temp < 0) {
                    return false;
                }
                if (checkFib(temp, temp, num, i+1)) {
                    return true;
                }
            }
            return false;
        }
        long last = 0;
        
        // i, j: First two integers length;
        for (int j = 1; j <= (length-1)/2; j++) {
            last = mov(last, num[j-1]-'0');
            long temp = 0;
            // The max length the second number can iterate.
            int maxLength = length-2*j > (length-1)/2 ? (length-1)/2 : length-2*j;
            for (int i=1; i <= maxLength; i++) {
                // Not continuous 0-prefix
                if (temp == 0 && i>=2) {
                    break;
                }
                temp = mov(temp, num[j+i-1]-'0');
                if (checkFib(temp+last, temp, num, j+i)) {
                    return true;
                }
            }
        }
        return false;
    }
};