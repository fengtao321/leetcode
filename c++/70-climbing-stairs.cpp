class Solution {
public:
    int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int llast = 1;
        int last = 2;
        int temp;
        for (int i = 2; i < n; i++) {
            temp = llast + last;
            llast = last;
            last = temp;
        }
        return last;
    }
};