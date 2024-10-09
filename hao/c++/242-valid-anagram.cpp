class Solution {
public:
    bool isAnagram(string s, string t) {
        int letters[26];
        memset(letters, 0, 26*sizeof(int));
        int length = s.size();
        if (t.size() != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            letters[s[i]-'a']++;
        }
        for (int i = 0; i < length; i++) {
            letters[t[i]-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(letters[i] != 0) {
                return false;
            }
        }
        return true;
    }
};