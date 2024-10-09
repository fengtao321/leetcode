class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        transactions = []
        
        def sumTransactions():
            ans = 0
            for t in transactions:
                ans += prices[t[1]] - prices[t[0]]
            return ans
    
        def mergeTransactions():
            min_sum = prices[transactions[len(transactions)-1][1]] - prices[transactions[len(transactions)-1][0]]
            index = len(transactions)-1
            need_merge = False 

            for i in range(len(transactions)-1):
                val = prices[transactions[i][1]] - prices[transactions[i+1][0]]
                if val < min_sum:
                    min_sum = val
                    index = i
                    need_merge = True

                val = prices[transactions[i][1]] - prices[transactions[i][0]]
                if val < min_sum:
                    min_sum = val
                    index = i
                    need_merge = False
                
            if need_merge:
                transactions[index+1][0] = transactions[index][0]
            transactions.pop(index)
                
            
        l = -1
        for r in range(len(prices)-1):
            price = prices[r]
            if l < 0 or price < prices[l]:
                l = r
                continue
            
            if price > prices[r+1]:
                transactions.append([l, r])
                l = -1
                
        if l>=0 and prices[len(prices)-1] > prices[l]:
            transactions.append([l, len(prices)-1])
            
        while len(transactions) >k:
            mergeTransactions()
        
        return sumTransactions()
            
            
            
            