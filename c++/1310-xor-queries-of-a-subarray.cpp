class Solution {
public:
    vector<int> xorQueries(vector<int>& arr, vector<vector<int>>& queries) {
        unsigned short size = arr.size();
        unsigned short qSize = queries.size();
        int* xorArray = (int*)calloc(size+1, 4);
        unsigned short i = 0;
        int temp = 0;
        vector<int> retArray(qSize);
        for (;i < size;) {
            temp ^= arr[i];
            xorArray[++i] = temp;
        }

        for (i = 0; i < qSize; i++) {
            retArray[i] = xorArray[queries[i][1] + 1] ^ xorArray[queries[i][0]];
        }
        free(xorArray);

        return retArray;
    }
};