class Solution {
public:
    int fib(int n) {
        if (n==0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int sum[n+1];
        sum[0] = 0;
        sum[1] = 1;
        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i-1] + sum[i-2];
        }
        return sum[n];
    }
};