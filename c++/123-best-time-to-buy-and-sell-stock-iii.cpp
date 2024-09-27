class Solution {
public:
    int maxProfit(vector<int>& prices) {
        // Step 1: Get DP
        // Step 2: Get second SP
        // Assume DP is from a->b, then SP is from the maximum
        // (1):DP(n) n < a
        // (2):DP(n) start from b+1
        // (3):-dp(n) start from a n <= b
        
        // Step 1: Get DP
        int length = prices.size();
        int last = 0;
        int maxProfit = 0;
        int rightbound =0;
        for (int i = 1; i < length; i++) {
            last = last + prices[i]-prices[i-1];
            if (prices[i] > prices[i-1]) {
                
                if (last > maxProfit) {
                    maxProfit = last;
                    rightbound = i;
                }
            } else if (last < 0){
                last = 0;
            }
            
        }
        // Step 2: Find Minimum price
        int leftbound = 0;
        int minprice = prices[0];
        for (int i = 1; i < rightbound; i++) {
            if (prices[i] < minprice) {
                leftbound = i;
                minprice = prices[i];
            }
        }

        int submax = 0;
        // Step 3: Find DPmax from 0-leftbound;
        last = 0;
        for (int i = 1; i <leftbound; i++) {
            last = last + prices[i]-prices[i-1];
            if (prices[i] > prices[i-1]) {
                
                if (last > submax) {
                    submax = last;
                }
            } else if (last < 0) {
                last = 0;
            }
        }
        
        // Step 4: Find -DPmax from leftbound-rightbound
        last = prices[leftbound+1];
        for (int i = leftbound+2; i < rightbound; i++) {
            if (prices[i] > last) {
                last = prices[i];
            } else {
                int profit = last-prices[i];
                if (profit > submax) {
                    submax = profit;
                }
            }
        }
        cout << submax;
        // Step 5: Find DPmax from rightbound+1 - n
        if (rightbound >= length-2) {
            return maxProfit+submax;
        }
        last = prices[rightbound+1];
        for (int i = rightbound+2; i < length; i++) {
            if (prices[i] < last) {
                last = prices[i];
            } else {
                int profit = prices[i]-last;
                if (profit > submax) {
                    submax = profit;
                }
            }
        }
        return maxProfit + submax;
    }
};