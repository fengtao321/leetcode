class Solution:
    def splitArray(self, nums: List[int], k: int) -> int:
        N = len(nums)
        if k>N:return 0
       
        
        pre_sum = [0]*(N + 1)
        for i in range(N):
            pre_sum[i+1] =   pre_sum[i] + nums[i]

        @lru_cache(maxsize = None)
        def dfs(i, k):
            if k == 1:
                return pre_sum[N] - pre_sum[i]
                
            curr= 10**9
            for j in range(i, N-k+1):
                pre = pre_sum[j+1] - pre_sum[i]
                if pre > curr: break
                curr = min(curr, max(pre, dfs(j+1, k-1)))
                                    
            return curr

        return dfs(0,k)
                
            