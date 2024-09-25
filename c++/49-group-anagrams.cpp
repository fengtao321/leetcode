class Solution {
public:
    string uniqueString(string s) {
        string news = "";
        char counts[26] = {0};
        for (int i = 0; i < s.length(); i++) {
            counts[s[i]-'a']++;
        }
        for (char i = 0; i < 26; i++) {
            for (char j = 0; j <counts[i]; j++) {
                news += ('a' + i);
            }
        }
        return news;
    }
    
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> groups;
        unordered_map<string, vector<string>> result_map;
        for (int i =0 ; i < strs.size(); i++) {
            string news = uniqueString(strs[i]);
            result_map[news].push_back(strs[i]);
        }
        for (auto it = result_map.begin(); it != result_map.end(); it++) {
            groups.push_back(it->second);
        }
        return groups;
    }
};