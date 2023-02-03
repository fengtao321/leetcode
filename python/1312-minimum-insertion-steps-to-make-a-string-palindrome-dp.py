class Solution:
    def minInsertions(self, s: str) -> int:
        N = len(s)
        
        @lru_cache(maxsize=None)
        def dfs(i, j):
            if i>j:
                return 0
            if i==j:
                return 1
                        
            if s[i] == s[j]:
                return 2+dfs(i+1, j-1)

            return max(dfs(i+1, j), dfs(i, j-1))
                        
        return N-dfs(0, N-1)
            