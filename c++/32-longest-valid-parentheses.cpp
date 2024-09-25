class Solution {
public:
    int longestValidParentheses(string s) {
        int maxLength = 0;
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        int dp[length];
        memset(dp, 0, length*sizeof(int));
        if (s[0] == '(' && s[1] == ')') {
            dp[1] = 2;
            maxLength = 2;
        }
        for (int i = 2; i < length; i++) {
            if (s[i] == ')') {
                if (s[i-1] == ')') {
                    int temp = i-dp[i-1]-1;
                    if (temp >= 0) {
                        if (s[temp] == '(') {
                            dp[i] = dp[i-1] + 2 + (temp-1 >=0 ? dp[temp-1] : 0);
                        }
                    }
                }
                else {
                    dp[i] = dp[i-2] + 2;
                }
            }
            maxLength = dp[i] > maxLength ? dp[i] : maxLength;
            // cout << dp[i];
        }
        
         
        return maxLength;
    }
};