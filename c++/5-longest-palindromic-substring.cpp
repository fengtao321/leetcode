class Solution {
public:
    string longestPalindrome(string s) {
        int maxLength = 0;
        int length = s.length();
        int j = 1;
        int tempLength = 1;
        int bestStart = 0;
        for (int i = 0; i < length; i++) {
            // Length: odd
            j = 1;
            while (true) {
                if (i-j < 0 || i+j > length-1 || s[i-j] != s[i+j]) {
                    break;
                }
                j++;
            }
            tempLength = 2*j-1;
            if (tempLength > maxLength) {
                maxLength = tempLength;
                bestStart = i-j+1;
            }
            
            // Length: Even
            if (s[i] != s[i+1] || i == length - 1) {
                continue;
            }
            j = 1;
            while (true) {
                
                if (i-j < 0 || i+j+1 > length-1 || s[i-j] != s[i+j+1]) {
                    break;
                }
                j++;
            }
            tempLength = 2*j;
            if (tempLength > maxLength) {
                maxLength = tempLength;
                bestStart = i-j+1;
            }
        }
        
        // Output
        string t = s.substr(bestStart, maxLength);
        return t;
    }
    
};