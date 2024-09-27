class Solution {
public:
    vector<string> removeAnagrams(vector<string>& words) {
        vector<string> savewords; // Ultimate
        
        vector<int> lastone(26,0); // Count for one word
        // Initialize
        string first = words[0];
        // Count times
        for (int i = 0; i < first.length(); i++) {
            lastone[first[i]-'a']++;
        }
        // Save
        savewords.push_back(first);
        // For each word
        for (int j = 1; j < words.size(); j++) {
            // Count times
            first = words[j];
            vector<int> one(26,0);
            for (int i = 0; i < first.length(); i++) {
                one[first[i]-'a']++;
            }
            // Compare near;
            for (int i = 0; i < 26; i++) {
                if (lastone[i] != one[i]) {
                    savewords.push_back(first);
                    break;
                }
            }
            // Change value;
            lastone = one;
            
        }
        return savewords;
    }
};