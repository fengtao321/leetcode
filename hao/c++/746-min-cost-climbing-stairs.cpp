class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        
        cost.push_back(0);
        
        int llast = cost[0];
        int last = cost[1];
        if (cost.size() == 3) {
            return llast > last? last:llast;
        }
        for (int i = 2; i < cost.size(); i++) {
            int temp = cost[i] + (llast > last? last : llast);
            llast = last;
            last = temp;
        }
        return last;
    }
};