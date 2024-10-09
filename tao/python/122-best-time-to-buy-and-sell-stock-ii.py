class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        ans, buy = 0, -1
        for i in range(len(prices) - 1):
            if buy >=0 and  prices[i] > buy and prices[i+1] < prices[i]:
                ans += prices[i] - buy
                buy = -1
            if buy < 0 and prices[i+1] > prices[i]:
                buy = prices[i]
        
        if buy >= 0 and prices[-1] > buy:
            return ans + prices[-1] - buy
        return ans