class Solution {
public:
    string convert(string s, int numRows) {
        
        if (numRows == 1) {
            return s;
        }
        int length = s.length();
        int cir = 2*numRows-2;
        int groups = length / cir;
        int remain = length % cir;
        string t;
        if (numRows == 2) {   
            t = s;
            int temp = groups+remain;
            for (int i = 0; i < groups; i++) {
                t[i] = s[i*2];
                t[i+temp] = s[i*2+1];            
            }
            if (remain != 0) {
                t[groups] = s[groups*2];
            }
            return t;
        }
        
        int index = 0;
        vector<string> ss(numRows);
        
        int i;
        
        int safe = length-remain;
        
        while (index < safe) {
            
            for (i = 0; i < numRows; i++) {
                ss[i] += s[index++];
            }
            
            for (i = numRows-2; i > 0; i--) {
                ss[i] += s[index++];
            }
        }
        for (i = 0; i < numRows && index < length; i++) {
            ss[i] += s[index++];
        }
            
        for (i = numRows-2; i > 0 && index < length; i--) {
            ss[i] += s[index++];
        }
        t = ss[0];
        for (i = 1; i < numRows; i++) {
            t += ss[i];
        }
        return t;
        
    }
};