class Solution {
public:
    int myAtoi(string s) {
        int length = s.length();
        int value = 0;
        int sign = 1;
        int i = 0;
        char a;
        
        while (i < length) {
            a = s[i++];
            if (a != ' ') {
                if (a == '.') {
                    return 0;
                }
                if (a == '-') {
                    sign = -1;
                }
                else if (a != '+') {
                    i--;
                }
                break;
            }
        }
        int n;
        while (i < length) {
            a = s[i++];
            if (a >= '0' && a <= '9') {
                n = a - '0';
                if (value > 214748364) {
                    if (sign==1) {
                        return 2147483647;
                    } else {
                        return -2147483648;
                    }
                } else if (value == 214748364) {
                    if (sign == 1 && n >= 7) {
                        return 2147483647; 
                    }
                    if (sign == -1 && n >= 8) {
                        return -2147483648; 
                    }
                }
                value = value*10 +n;

                
            } else {
                break;
            }
        }
        return value * sign;
        
    }
};