class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int last = prices[0];
        
        int maxProfit = 0;
        for (vector<int>::iterator it = prices.begin(); it != prices.end(); ++it) {
            if (*it < last) {
                last = *it;
            } else {
                int profit = *it - last;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
};