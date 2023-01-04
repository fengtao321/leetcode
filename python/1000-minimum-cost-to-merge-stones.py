class Solution:
    def mergeStones(self, stones: List[int], k: int) -> int:
        if k==0:
            return 0
        
        n = len(stones)
        if (n-1) % (k-1) !=0:
            return -1
        
        prefixSum = [0] * (n + 1)
        for i in range(1, n+1):
            prefixSum[i] = prefixSum[i-1] + stones[i-1]
        
        @lru_cache(maxsize=None)
        def dfs(l, r):
            if r+1-l < k:
                return 0
                        
            minCost = 1000000
            for i in range(l, r, k-1):
                minCost = min(minCost,  dfs(l, i) +  dfs(i+1, r))

            if  (r-l) % (k-1) == 0:
                minCost += prefixSum[r+1] - prefixSum[l];

            return minCost
        
        return dfs(0,n-1)