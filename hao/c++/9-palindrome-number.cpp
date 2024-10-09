class Solution {
public:
    bool isPalindrome(int x) {
        if (x<0) {
            return false;
        }
        if (x<10) {
            return true;
        }
        if (x%10==0) {
            return false;
        }
        int lastX;
        int a[10];
        int i=0;
        while(x > 0) {
            a[i++] = x%10;
            x = x/10;
        }
        for (int k = 0; k < i/2; k++) {
            if (a[k] != a[i-1-k]) {
                return false;
            }
        }
        return true;
    }
};