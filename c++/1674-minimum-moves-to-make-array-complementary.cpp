class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        
        unsigned int size = nums.size();
        unsigned int* nodes = (unsigned int*)malloc(6*size); // sizeof(int)*3/2
        // Loop the nums vector
        unsigned int j = 0;
        for (unsigned short i = 0; i < size/2; i++) {
            nodes[j++] = min(nums[i], nums[size-1-i]) + 1;
            nodes[j++] = max(nums[i], nums[size-1-i]) + limit;
            nodes[j++] = nums[i] + nums[size-1-i];
        }
        /*for (unsigned short i = 0; i < size*3/2; i++) {
            cout << nodes[i] << endl;
        }*/
        unsigned int i = 0;
        unsigned int max = 0;
        for (unsigned short i = 0; i < size*3/2; i++) {
            unsigned sum = 0;
            for (j = 0; j < 3*size/2; j+=3) {
                if (nodes[i] == nodes[j+2]) {
                    sum += 2;
                } else if (nodes[j] <= nodes[i] && nodes[i] <= nodes[j+1]) {
                    sum += 1;
                }
            }
            if (sum > max) {
                max = sum;
            }
        }
        return size - max;
    }
};