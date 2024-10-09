class Solution {
public:
    typedef struct {
        int min = -1;
        int max = -1;
    } Counter;
    int findTheLongestSubstring(string s) {
        uint8_t key = 0;
        Counter counter[32];
        int length = s.length();
        int i,j;
        for (i = 0; i < length; i++) {
            switch(s[i]) {
                case 'a': {
                    key ^= 1;
                }
                case 'e': {
                    key ^= 2;
                }
                case 'i': {
                    key ^= 4;
                }
                case 'o': {
                    key ^= 8;
                }
                case 'u': {
                    key ^= 16;
                }
                default:
                    break;
            }
            counter[key].max = i;
            if (counter[key].min == -1) {
                counter[key].min = i;
            }
        }
        length = -1;
        counter[0].min = -1;
        for (i = 0; i < 32; i++) {
            j = counter[i].max - counter[i].min;
            if (j > length) {
                length = j;
            }
        }
        return length;
    }
};