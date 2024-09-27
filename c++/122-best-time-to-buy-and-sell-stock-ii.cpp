class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int last = prices[0];
        
        int maxProfit = 0;
        for (vector<int>::iterator it = prices.begin()+1; it != prices.end(); ++it) {
            if (*it > last) {
                maxProfit += *it-last;
            }
            last = *it;
        }
        return maxProfit;
    }
};