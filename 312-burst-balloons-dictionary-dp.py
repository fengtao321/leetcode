class Solution:
    def maxCoins(self, nums: List[int]) -> int:
        dp = {}
        nums = [1] + nums + [1]
        # nums[:0] = [1]
        # nums.append(1)
        
        def dfs(l,r)-> int:
            if l > r:
                return 0
            
            if (l,r) in dp:
                return dp[l,r]
            
            result = 0
            for i in range(l, r+1):
                result = max(result, nums[i]*nums[l-1]*nums[r+1] + dfs(l, i-1) + dfs(i+1, r))
            dp[(l, r)] = result
            return result
            
          
        return dfs(1, len(nums)-2) 