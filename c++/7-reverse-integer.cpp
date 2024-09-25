class Solution {
public:
    int reverse(int x) {
        if (x <= 9 && x >= -9) {
            return x;
        }
        if (x >= 2147483642 || x <= -2147483642) {
            return 0;
        }
        int mod = 1;
        if (x < 0) {
            x = -x;
            mod = -1;
        }
        
        int value = 0;
        int v = 1;
        int i;
        for (i = 1; i < 10; i++) {
            v*=10;
            if (v>x) {
                break;
            }
        }
        if (i <= 9) {
            v/=10;
            int res;
            while (x>0) {
            
                res = x%10;
                x /=10;
                value += res*v;
                v/=10;
            }
            return value * mod;
        } else {
            int a[10] = {8,4,6,3,8,4,7,4,1,2};
            // if (mod == 1) {
            //     a[0] = 7;
            // }
            int res;
            bool compare = true;
            while (x>0) {
                res = x%10;
                if (compare) {
                    if (res > a[--i]) {
                        return 0;
                    } else if (res < a[i]) {
                        compare = false;
                    }
                }
                x /=10;
                value += res*v;
                v/=10;
            }
            return value * mod;
        }
        
        
    }
};