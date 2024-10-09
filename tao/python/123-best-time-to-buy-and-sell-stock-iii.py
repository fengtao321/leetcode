class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        temp_max =  temp_min = prices[-1]
        max_from_back= [0] * (len(prices) + 1)
        
        for i in range(len(prices)-2, 0, -1):
            price = prices[i]
            if price > temp_max:
                temp_min = temp_max = price
            else:
                temp_max = price
                temp_min = min(price, temp_min)            
            max_from_back[i] = max(max_from_back[i+1], temp_max - temp_min)
        print(max_from_back)
        ans = 0
        temp_min = prices[0]
        for i in range(1, len(prices)):
            price = prices[i]
            if price > temp_min:
                ans = max(ans, max_from_back[i+1] + price - temp_min)
            if price < temp_min:
                temp_min = price
                
        return ans