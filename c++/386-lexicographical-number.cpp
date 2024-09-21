class Solution {
public:
    void appendLexical(vector<int>& vec, int current, int n) {
        int temp = (current == 0 ? 1 : current * 10);
        int limit = min(n, current*10+9);
        for (; temp <= limit ; temp++) {
            vec.push_back(temp);
            appendLexical(vec, temp, n);
        }
    }

    vector<int> lexicalOrder(int n) {
        vector<int> result;
        appendLexical(result, 0, n);
        return result;
    }
};