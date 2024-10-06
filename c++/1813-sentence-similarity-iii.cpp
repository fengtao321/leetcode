class Solution {
public:
    bool areSentencesSimilar(string sentence1, string sentence2) {
        vector<string> tokens1;
        vector<string> tokens2;
        vector<string>* small;
        vector<string>* large;
        string token;
        stringstream s1(sentence1);
        stringstream s2(sentence2);
        while(getline(s1, token, ' ')) {
            tokens1.push_back(token);
        }
        while(getline(s2, token, ' ')) {
            tokens2.push_back(token);
        }

        if (tokens2.size() < tokens1.size()) {
            small = &tokens2;
            large = &tokens1;
        } else {                    
            small = &tokens1;
            large = &tokens2;
        }
        vector<string>::iterator small_it = small->begin();
        vector<string>::iterator large_it = large->begin();
        uint8_t count = small->size();
        // Find from the beginning
        for (; small_it != small->end(); small_it++, large_it++, count--) {
            if (*large_it != *small_it) {
                break;
            }
        }
        if (count == 0) return true;
        small_it = small->end() - 1;
        large_it = large->end() - 1;        
        for (; count > 0; large_it--, small_it--, count--) {
            if (*large_it != *small_it) {
                break;
            }
        }

        return count == 0;
    }
};