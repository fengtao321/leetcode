class Solution {
public:
    int uniquePaths(int m, int n) {
        int steps = n+m-2;
        long way = 1;
        if (n==1 || m==1) {
            return 1;
        }
        if (m>n) {
            for (int i = 1; i < n; i++) {
                way = way * (steps-i+1) / i;
            }
        } else {
            for (int i = 1; i < m; i++) {
                way = way * (steps-i+1) / i;
            }
        }
        return (int)way;
    }
};