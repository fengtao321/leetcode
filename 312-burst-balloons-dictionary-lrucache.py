class Solution:
    def maxCoins(self, nums: List[int]) -> int:
        nums = [1] + nums + [1]
        # nums[:0] = 1
        # nums.append(1)
        
        @lru_cache(maxsize=None)
        def dfs(l,r)-> int:
            if l > r:
                return 0
            
            curr = 0
            for i in range(l, r+1):
                curr =  max(curr, nums[i]*nums[l-1]*nums[r+1] + dfs(l, i-1) + dfs(i+1, r))
            return curr
            
          
        return dfs(1, len(nums)-2)  