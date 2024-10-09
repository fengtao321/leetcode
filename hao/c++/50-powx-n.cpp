class Solution {
public:
    double myPow(double x, int n) {
        if (x == 0) return 0;
        bool sym = n > 0;
        n = sym ? n : (unsigned int)(~n)+1;
        double result = n % 2 == 0 ? 1 
                                       : sym ? x
                                             : 1 / x;
        double temp = x;
        unsigned int limit = n < 2147483648 ? n : 2147483648;
        n = n / 2;
        for (unsigned int i = 1; i < limit; i*=2) {
            temp = temp * temp;
            if (n & 0x1 == 1) {
                if (sym) result *= temp;
                else     result /= temp;
            }
            n = n / 2;    
        }

        return result;
    }
};

// Runtime: 0ms 100%
// Memory: 8.01MB 97.33%
// Link: https://leetcode.com/problems/powx-n/submissions/1400161895/